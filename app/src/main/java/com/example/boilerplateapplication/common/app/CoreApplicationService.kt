package com.example.boilerplateapplication.common.app

import android.app.Application
import com.example.appstores.sources.dependencies.HasDependencies
import com.example.boilerplateapplication.common.AppDependency
import com.example.coreandroid.sources.CoreType
import com.example.coreandroid.sources.controls.ApplicationService

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
class CoreApplicationService(private val application: Application): ApplicationService, HasDependencies {
    private val core: CoreType by lazy {
        dependencies.resolveCore()
    }

    override fun onCreate() {
        core.configure(application, AppDependency())
    }
}