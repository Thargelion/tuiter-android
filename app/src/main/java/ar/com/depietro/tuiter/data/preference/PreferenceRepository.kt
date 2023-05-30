package ar.com.depietro.tuiter.data.preference

interface PreferenceRepository {
    fun getUserName(): String
    fun setUserName(userName: String)
    fun getUserId(): Int
    fun setUserId(userId: Int)
}