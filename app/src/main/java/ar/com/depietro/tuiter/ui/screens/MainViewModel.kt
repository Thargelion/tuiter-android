package ar.com.depietro.tuiter.ui.screens

import androidx.lifecycle.ViewModel
import ar.com.depietro.tuiter.data.user.UserRepository
import ar.com.depietro.tuiter.data.user.model.User
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    userRepository: UserRepository
) : ViewModel() {
    private val userId: String = ""

    private val userStrear: Flow<User> = userRepository.getUser(userId)


}