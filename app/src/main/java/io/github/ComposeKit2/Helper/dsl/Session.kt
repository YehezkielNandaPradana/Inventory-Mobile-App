package io.github.ComposeKit2.Helper.dsl

import android.content.Context
import android.content.SharedPreferences

object Session {

    private const val PREF_NAME = "session"

    private const val KEY_WORKER_ID = "workerId"
    private const val KEY_FULL_NAME = "fullName"
    private const val KEY_TOKEN = "token"

    private lateinit var pref: SharedPreferences

    fun init(context: Context) {
        pref = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun login(
        workerId: Int,
        fullName: String = "",
        token: String = ""
    ) {
        pref.edit()
            .putInt(KEY_WORKER_ID, workerId)
            .putString(KEY_FULL_NAME, fullName)
            .putString(KEY_TOKEN, token)
            .apply()
    }

    fun logout() {
        pref.edit().clear().apply()
    }

    val workerId: Int
        get() = pref.getInt(KEY_WORKER_ID, 0)

    val fullName: String
        get() = pref.getString(KEY_FULL_NAME, "") ?: ""

    val token: String
        get() = pref.getString(KEY_TOKEN, "") ?: ""

    val isLogin: Boolean
        get() = workerId > 0
}