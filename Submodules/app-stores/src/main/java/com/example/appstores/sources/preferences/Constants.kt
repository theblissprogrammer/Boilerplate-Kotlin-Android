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
        Environment.DEVELOPMENT -> byteArrayOf(43, 37, 12, 59, 7, 25, 17, 60, 54, 78, 16,
                10, 29, 27, 66, 63, 40, 6, 87, 39, 50, 31, 11, 59, 81, 13,
                1, 44, 71, 102, 101, 62)
        Environment.PRODUCTION -> byteArrayOf(43, 37, 12, 35, 11, 28, 0, 60, 50, 29,
                73, 5, 7, 0, 36, 69, 88, 20, 35, 14, 98, 40, 106, 58, 53, 5, 20, 34,
                27, 75, 109, 34)
    })
}

val Constants.Companion.stripeAPIKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(43, 37, 12, 59, 7, 25, 17, 60, 54, 78, 16,
                10, 29, 27, 66, 63, 40, 6, 87, 39, 50, 31, 11, 59, 81, 13,
                1, 44, 71, 102, 101, 62)
        Environment.PRODUCTION -> byteArrayOf(43, 37, 12, 35, 11, 28, 0, 60, 50, 29,
                73, 5, 7, 0, 36, 69, 88, 20, 35, 14, 98, 40, 106, 58, 53, 5, 20, 34,
                27, 75, 109, 34)
    })
}

val Constants.Companion.intercomAppKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(56, 34, 43, 32, 6, 3, 31, 11)
        Environment.PRODUCTION -> byteArrayOf(58, 123, 61, 45, 19, 93, 84, 85)
    })
}

val Constants.Companion.intercomAPIKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(58, 32, 55, 61, 13, 3, 1, 60, 7, 72, 75,
                99, 99, 103, 66, 69, 10, 90, 6, 105, 107, 45, 48, 124, 91, 8, 82, 7, 22,
                29, 68, 122, 55, 107, 71, 65, 90, 89, 86, 110, 110, 44, 102, 120, 90, 9,
                92, 6, 16, 20, 67, 120)
        Environment.PRODUCTION -> byteArrayOf(58, 32, 55, 61, 13, 3, 1, 60, 7, 72, 75,
                99, 107, 48, 23, 16, 91, 93, 81, 107, 62, 119, 54, 41, 82, 82, 6, 91,
                69, 25, 68, 124, 98, 99, 66, 75, 15, 89, 95, 109, 98, 125, 48, 42, 84,
                93, 87, 5, 77, 77, 70, 123)
    })
}

val Constants.Companion.segmentKey by lazy {
    unobfuscate(key = when (Environment.mode) {
        Environment.DEVELOPMENT -> byteArrayOf(21, 34, 48, 25, 13, 47, 55, 84, 61, 90,
                104, 11, 100, 23, 51, 48, 24, 58, 35, 50, 31, 3, 0, 24, 47, 18, 9, 14,
                50, 29, 115, 126)
        Environment.PRODUCTION -> byteArrayOf(106, 121, 107, 55, 55, 30, 40, 2, 4, 103,
                83, 39, 103, 57, 21, 25, 28, 23, 33, 108, 110, 58, 63, 46, 9, 63, 32,
                33, 49, 104, 122, 58)
    })
}