package com.example.appstores.sources.preferences

import com.example.coreandroid.sources.enums.Environment
import com.example.coreandroid.sources.preferences.Constants
import com.example.coreandroid.sources.preferences.Constants.Companion.unobfuscate
import com.example.coreandroid.sources.preferences.ConstantsType

val ConstantsType.stripeAPIKey: String
    get() = Constants.stripeAPIKey
val ConstantsType.intercomAppKey: String
    get() = Constants.intercomAppKey
val ConstantsType.intercomAPIKey: String
    get() = Constants.intercomAPIKey
val ConstantsType.segmentKey: String
    get() = Constants.segmentKey
val ConstantsType.youtubeAPIKey: String
    get() = Constants.youtubeAPIKey

val Constants.Companion.youtubeAPIKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(0)
        Environment.PRODUCTION -> byteArrayOf(0)
    })
}

val Constants.Companion.stripeAPIKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(0)
        Environment.PRODUCTION -> byteArrayOf(0)
    })
}

val Constants.Companion.intercomAppKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(0)
        Environment.PRODUCTION -> byteArrayOf(0)
    })
}

val Constants.Companion.intercomAPIKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(0)
        Environment.PRODUCTION -> byteArrayOf(0)
    })
}

val Constants.Companion.segmentKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(0)
        Environment.PRODUCTION -> byteArrayOf(0)
    })
}