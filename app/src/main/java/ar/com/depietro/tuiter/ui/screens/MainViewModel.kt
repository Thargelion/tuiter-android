package ar.com.depietro.tuiter.ui.screens

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ar.com.depietro.tuiter.core.asResult
import ar.com.depietro.tuiter.core.Result
import ar.com.depietro.tuiter.data.preference.PreferenceRepository
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import ar.com.depietro.tuiter.data.tuit.repository.PagingUserPostSource
import ar.com.depietro.tuiter.data.tuit.repository.TuitRepository
import ar.com.depietro.tuiter.data.user.repository.UserRepository
import ar.com.depietro.tuiter.data.user.model.User
import ar.com.depietro.tuiter.ui.components.TuitViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface UserUIState {
    data class Success(val user: User) : UserUIState
    object Loading : UserUIState
    data class Error(val message: String) : UserUIState
}

data class MainViewUIState(
    val userState: UserUIState = UserUIState.Loading
)

enum class ListState {
    IDLE,
    LOADING,
    PAGINATING,
    ERROR,
    PAGINATION_EXHAUST,
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tuitRepository: TuitRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _userState = MutableStateFlow(UserUIState.Loading)

    private val _uiState = MutableStateFlow(
        MainViewUIState(_userState.value)
    )
    val uiState = _uiState.asStateFlow()
    val listState by mutableStateOf(ListState.IDLE)
    fun getUserPosts(): Flow<PagingData<UserTuit>> =
        tuitRepository.getPagedUserTuits(preferenceRepository.getUserId()).cachedIn(viewModelScope)

    init {
        if (preferenceRepository.getUserName() != "" && preferenceRepository.getUserId() != -1) {
            fetchUser(preferenceRepository.getUserId())
        } else {
            _uiState.value =
                MainViewUIState(UserUIState.Error("Create A New User 😎"))
        }
    }

    fun fetchUser(userId: Int) {
        viewModelScope.launch {
            userRepository.getUser(userId).asResult()
                .collect { result ->
                    val userUiState = when (result) {
                        is Result.Loading -> UserUIState.Loading
                        is Result.Success -> {
                            preferenceRepository.setUserName(result.data.userName)
                            preferenceRepository.setUserId(result.data.id)
                            UserUIState.Success(result.data)
                        }

                        is Result.Error -> UserUIState.Error(result.exception?.message ?: "")
                    }
                    _uiState.value = MainViewUIState(userUiState)
                }
        }
    }

    fun createUser(userName: String) {
        viewModelScope.launch {
            userRepository.create(User(userName = userName)).asResult()
                .collect { result ->
                    val userUiState = when (result) {
                        is Result.Success -> {
                            preferenceRepository.setUserName(result.data.userName)
                            preferenceRepository.setUserId(result.data.id)
                            UserUIState.Success(result.data)
                        }

                        is Result.Loading -> UserUIState.Loading
                        is Result.Error -> UserUIState.Error(result.exception?.message ?: "")
                    }
                    _uiState.value = MainViewUIState(userUiState)
                }
        }
    }

    fun likeTuit(id: Int) {
        TODO("Not yet implemented")
    }
}

fun UserTuit.asUserTuitViewModel(): TuitViewData {
    return TuitViewData(
        id = id,
        authorName = author,
        message = message,
        avatarUrl = avatarUrl,
        liked = liked,
        likes = likes,
    )
}
