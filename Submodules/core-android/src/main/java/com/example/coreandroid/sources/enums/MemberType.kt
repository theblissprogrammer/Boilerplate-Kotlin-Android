package com.example.coreandroid.sources.enums

import com.google.gson.annotations.SerializedName

/**
 * Created by ahmedsaad on 2017-12-01.
 * Copyright © 2017. All rights reserved.
 */
enum class MemberType {
    @SerializedName("User")
    User,
    @SerializedName("Professional")
    Professional,
    @SerializedName("Admin")
    Admin
}