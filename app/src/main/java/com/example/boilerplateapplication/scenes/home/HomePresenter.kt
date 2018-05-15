package com.example.boilerplateapplication.scenes.home

import com.example.boilerplateapplication.scenes.home.common.HomeDisplayable
import com.example.boilerplateapplication.scenes.home.common.HomePresentable
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class HomePresenter(private val fragment: WeakReference<HomeDisplayable?>) : HomePresentable {

    private val blogDateFormatter = SimpleDateFormat("MMMM d yyyy", Locale.US)
    private val dateTimeFormatter = SimpleDateFormat("E, MMM d, yyyy h:mma", Locale.US)

}