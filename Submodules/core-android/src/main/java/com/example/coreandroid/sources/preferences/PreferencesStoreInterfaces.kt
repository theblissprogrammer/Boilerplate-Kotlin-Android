package com.example.coreandroid.sources.preferences

import com.example.coreandroid.sources.enums.DefaultsKey

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

interface PreferencesStore {
    fun <T> get(key: DefaultsKey<T?>): T?
    fun <T> set(value: T?, key: DefaultsKey<T?>)
    fun <T> remove(key: DefaultsKey<T?>)
    fun clear()
}

abstract class PreferencesWorkerType: PreferencesStore