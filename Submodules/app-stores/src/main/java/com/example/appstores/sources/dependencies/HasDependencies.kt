package com.example.appstores.sources.dependencies

import android.app.Application
import com.example.coreandroid.sources.dependencies.CoreDependable
import com.example.coreandroid.sources.dependencies.DependencyConfigurator


/**
 * Created by ahmedsaad on 2017-11-30.
 * Copyright © 2017. All rights reserved.
 */
interface HasDependencies {
    /// Container for dependency instance factories
    val dependencies: StoreDependable
        get() {
            return DependencyInjector.dependencies
        }
}

/// Declare dependencies container to use
fun DependencyConfigurator.configure(application: Application, dependencies: StoreDependable) {
    dependencies.application = application

    configure(dependencies as CoreDependable)
    DependencyInjector.dependencies = dependencies
}

/// Used to pass around dependency container
/// which can be reassigned to another container
class DependencyInjector {
    companion object {
        var dependencies: StoreDependable = StoreDependency()
    }
}