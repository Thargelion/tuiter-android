package ar.com.depietro.tuiter.data.preference

import android.content.Context
import android.content.SharedPreferences
import ar.com.depietro.tuiter.data.preference.model.PREFERENCE_NAME
import ar.com.depietro.tuiter.data.preference.model.USER_ID
import ar.com.depietro.tuiter.data.preference.model.USER_NAME
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalSharedPreferenceRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    val gson: Gson
) :
    PreferenceRepository {
    private val pref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    private fun String.put(long: Long) {
        editor.putLong(this, long)
        editor.commit()
    }

    private fun String.put(int: Int) {
        editor.putInt(this, int)
        editor.commit()
    }

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.commit()
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.getLong() = pref.getLong(this, 0)

    private fun String.getInt() = pref.getInt(this, 0)

    private fun String.getString() = pref.getString(this, "")!!

    private fun String.getBoolean() = pref.getBoolean(this, false)

    override fun getUserName() = USER_NAME.getString()

    override fun setUserName(userName: String) = USER_NAME.put(userName)

    override fun getUserId() = USER_ID.getInt()

    override fun setUserId(userId: Int) = USER_ID.put(userId)
}