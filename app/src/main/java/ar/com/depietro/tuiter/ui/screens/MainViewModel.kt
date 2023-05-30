package ar.com.depietro.tuiter.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.depietro.tuiter.core.asResult
import ar.com.depietro.tuiter.core.Result
import ar.com.depietro.tuiter.data.preference.PreferenceRepository
import ar.com.depietro.tuiter.data.user.UserRepository
import ar.com.depietro.tuiter.data.user.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UserUIState {
    data class Success(val user: User) : UserUIState
    object Loading : UserUIState
    data class Error(val message: String) : UserUIState
}

data class MainViewUIState(
    val userState: UserUIState
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(MainViewUIState(UserUIState.Loading))
    val userState = _userState.asStateFlow()

    init {
        if (preferenceRepository.getUserName() != "") {
            fetchUser(preferenceRepository.getUserId())
        } else {
            _userState.value = MainViewUIState(UserUIState.Error("Create A New User 😎"))
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
                    _userState.value = MainViewUIState(userUiState)
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
                    _userState.value = MainViewUIState(userUiState)
                }
        }
    }
}

