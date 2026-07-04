package io.github.ComposeKit2.Usersession

object UserSession {
    var userId: Int = 0
    var userName: String = ""
    var email: String = ""
    var token: String = ""

    fun clear() {
        userId = 0
        userName = ""
        email = ""
        token = ""
    }
}