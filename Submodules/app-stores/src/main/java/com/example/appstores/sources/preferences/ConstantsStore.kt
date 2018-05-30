package com.example.appstores.sources.preferences

import com.example.appstores.R
import com.example.coreandroid.sources.enums.Environment
import com.example.coreandroid.sources.preferences.ConstantsStore


val ConstantsStore.baseURL: String
    get() = when (Environment.mode) {
        Environment.DEVELOPMENT -> get(R.string.base_url_debug, "" )
        Environment.PRODUCTION -> get(R.string.base_url, "" )
    }

val ConstantsStore.baseRESTPath: String
    get() = get(R.string.base_rest_path, "" )

val ConstantsStore.facebookShareURL: Int
    get() = R.string.facebook_share_url

val ConstantsStore.youtubeURL: String
    get() = get(R.string.youtube_data_url, "")

val ConstantsStore.supportEmailBody: Int
    get() = R.string.support_email_body

val ConstantsStore.forgotPasswordURL: String
    get() = baseURL + get(R.string.forgot_password_path, "")

val ConstantsStore.firebaseURL: String
    get() = when (Environment.mode) {
        Environment.DEVELOPMENT -> get(R.string.firebase_url_debug, "" )
        Environment.PRODUCTION -> get(R.string.firebase_url, "" )
    }

val ConstantsStore.blogURL: String
    get() = get(R.string.blog_url, "" )

val ConstantsStore.blogRSS: String
    get() = get(R.string.blog_rss, "" )

val ConstantsStore.isAnonymousEnabled: Boolean
    get() = get(R.string.is_anonymous_enabled, "false").toBoolean()