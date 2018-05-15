package com.example.coreandroid.sources.data.models

import com.google.firebase.firestore.ListenerRegistration

/**
 * Created by ahmedsaad on 2018-02-07.
 * Copyright © 2017. All rights reserved.
 */
class SubscriptionToken(val listener: ListenerRegistration) {
    fun remove() {
        listener.remove()
    }
}