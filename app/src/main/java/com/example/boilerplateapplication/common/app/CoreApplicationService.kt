package com.example.boilerplateapplication.common.app

import android.app.Application
import com.example.appstores.sources.dependencies.configure
import com.example.boilerplateapplication.common.AppDependency
import com.example.coreandroid.sources.controls.ApplicationService
import com.example.coreandroid.sources.dependencies.DependencyConfigurator

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
class CoreApplicationService(private val application: Application): ApplicationService, DependencyConfigurator {

    override fun onCreate() {
        configure(application = application, dependencies = AppDependency())
    }
}