package com.example.appstores.sources.dependencies

import com.example.appstores.sources.stores.videos.VideosStore
import com.example.appstores.sources.stores.videos.VideosWorkerType
import com.example.coreandroid.sources.dependencies.Dependency


/**
 * Created by ahmedsaad on 2017-11-29.
 * Copyright © 2017. All rights reserved.
 */
interface Dependency: Dependency {
    fun resolveVideosWorker(): VideosWorkerType
    fun resolveVideosNetworkStore(): VideosStore
}