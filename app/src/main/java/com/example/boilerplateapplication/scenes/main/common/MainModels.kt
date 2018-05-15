package com.example.boilerplateapplication.scenes.main.common

/**
 * Created by ahmedsaad on 2018-01-18.
 * Copyright © 2017. All rights reserved.
 */
sealed class MainModels {
    enum class OptionType{
        Home
    }

    class NavigationViewModel(
            val type: OptionType,
            val icon: Int,
            val title: String? = ""): MainModels()
}