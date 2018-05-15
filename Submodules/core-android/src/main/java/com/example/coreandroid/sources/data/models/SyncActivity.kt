package com.example.coreandroid.sources.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by ahmedsaad on 2017-12-07.
 * Copyright © 2017. All rights reserved.
 */
open class SyncActivity(
        @PrimaryKey
        var type: String = "",
        var lastPulledAt: Date? = null): RealmObject()