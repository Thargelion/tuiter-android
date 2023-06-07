package ar.com.depietro.tuiter.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.depietro.tuiter.core.asResult
import ar.com.depietro.tuiter.core.Result
import ar.com.depietro.tuiter.data.preference.PreferenceRepository
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import ar.com.depietro.tuiter.data.tuit.repository.TuitRepository
import ar.com.depietro.tuiter.data.user.repository.UserRepository
import ar.com.depietro.tuiter.data.user.model.User
import ar.com.depietro.tuiter.ui.components.TuitViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface UserUIState {
    data class Success(val user: User) : UserUIState
    object Loading : UserUIState
    data class Error(val message: String) : UserUIState
}

@Immutable
sealed interface TuitFeedUIState {
    data class Success(val tuits: List<TuitViewData>) : TuitFeedUIState
    object Loading : TuitFeedUIState
    data class Error(val message: String) : TuitFeedUIState
}

data class MainViewUIState(
    val userState: UserUIState = UserUIState.Loading,
    val tuitListState: TuitFeedUIState = TuitFeedUIState.Loading
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tuitRepository: TuitRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _userState = MutableStateFlow(UserUIState.Loading)
    private val _tuitListState = MutableStateFlow(TuitFeedUIState.Loading)

    private val _uiState = MutableStateFlow(
        MainViewUIState(_userState.value, _tuitListState.value)
    )
    val uiState = _uiState.asStateFlow()

    init {
        if (preferenceRepository.getUserName() != "") {
            fetchUser(preferenceRepository.getUserId())
        } else {
            _uiState.value =
                MainViewUIState(UserUIState.Error("Create A New User 😎"), _tuitListState.value)
        }
    }

    fun fetchUser(userId: Int) {
        viewModelScope.launch {
            userRepository.getUser(userId).asResult()
                .collect { result ->
                    val userUiState = when (result) {
                        is Result.Loading -> UserUIState.Loading
                        is Result.Success -> UserUIState.Success(result.data)
                        is Result.Error -> UserUIState.Error(result.exception?.message ?: "")
                    }
                    _uiState.value = MainViewUIState(userUiState, _tuitListState.value)
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

    fun fetchTuitList(page: Int) {
        viewModelScope.launch {
            tuitRepository.getPagedUserTuits(preferenceRepository.getUserId(), page).asResult()
                .collect { result ->
                    val tuitListUiState = when (result) {
                        is Result.Success -> {
                            val tuitViewData = result.data.map {
                                it.asUserTuitViewModel()
                            }
                            TuitFeedUIState.Success(tuitViewData)
                        }

                        is Result.Loading -> TuitFeedUIState.Loading
                        is Result.Error -> TuitFeedUIState.Error(result.exception?.message ?: "")
                    }
                    _uiState.value = MainViewUIState(_userState.value, tuitListUiState)
                }
        }
    }

    fun likeTuit(id: Int) {
        TODO("Not yet implemented")
    }
}

fun UserTuit.asUserTuitViewModel(): TuitViewData {
    return TuitViewData(
        Id = Id,
        AuthorName = Author,
        Message = Message,
        AvatarUrl = AvatarUrl,
        Liked = Liked,
        Likes = Likes,
    )
}
