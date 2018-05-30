package com.example.coreandroid.sources.preferences

import android.content.Context

class ConstantsResourceStore(val context: Context?): ConstantsStore {

    /// Retrieves the constant from the store that corresponds to the given key.
    ///
    /// - Parameter key: The key that is used to read the store item.
    override fun <T> get(key: Int, default: T): T {
        return when (default) {
            is String -> context?.getString(key) as? T ?: default
            else -> default
        }
    }
}