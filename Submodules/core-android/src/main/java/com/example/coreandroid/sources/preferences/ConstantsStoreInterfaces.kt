package com.example.coreandroid.sources.preferences

import com.example.coreandroid.R
import com.example.coreandroid.sources.enums.Environment

interface ConstantsStore {
    fun <T> get(key: Int, default: T): T
}

interface ConstantsType: ConstantsStore {
    val aesKey: String
    val logDNAKey: String
    val googlePlacesAPIKey: String
    val jwtSecretKey: String
    val chatApiKey: String
    val chatApplicationID: String
    val chatDatabaseUrl: String
    val chatProjectID: String
    val chatStorageBucket: String

    /// Email of admin used for users
    val emailUserAdmin: String
        get() {
            return when (Environment.mode) {
                Environment.DEVELOPMENT -> get(R.string.email_user_admin_debug, "")
                else -> get(R.string.email_user_admin, "")
            }
        }
}