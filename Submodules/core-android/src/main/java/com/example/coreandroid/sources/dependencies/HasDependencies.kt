package com.example.coreandroid.sources.dependencies

import io.realm.Realm


/**
 * Created by ahmedsaad on 2017-11-30.
 * Copyright © 2017. All rights reserved.
 */
 internal interface HasDependencies {

    /// Container for dependency instance factories
    val dependencies: CoreDependable
        get() {
            return DependencyInjector.dependencies
        }
}

interface DependencyConfigurator {

    /// Declare dependency container to use
    fun configure(dependencies: CoreDependable) {
        DependencyInjector.dependencies = dependencies

        // Configure Realm
        val context = dependencies.resolveContext()
        if (context != null) {
            Realm.init(context)
        }
    }
}

/// Used to pass around dependency container
/// which can be reassigned to another container
class DependencyInjector {
    companion object {
        var dependencies: CoreDependable = CoreDependency()
    }
}