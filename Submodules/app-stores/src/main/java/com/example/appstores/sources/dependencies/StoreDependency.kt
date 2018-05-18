package com.example.appstores.sources.dependencies

import com.example.appstores.sources.AppStore
import com.example.appstores.sources.stores.videos.VideosNetworkStore
import com.example.appstores.sources.stores.videos.VideosStore
import com.example.appstores.sources.stores.videos.VideosWorker
import com.example.appstores.sources.stores.videos.VideosWorkerType
import com.example.coreandroid.sources.CoreType
import com.example.coreandroid.sources.dependencies.CoreDependency

open class StoreDependency: CoreDependency(), Dependency {
    override fun resolveCore(): CoreType {
        return AppStore()
    }

    override fun resolveVideosWorker(): VideosWorkerType {
        return VideosWorker(
                store = resolveVideosNetworkStore()
        )
    }

    override fun resolveVideosNetworkStore(): VideosStore {
        return VideosNetworkStore(
                httpService = resolveHTTPService(),
                preferencesWorker = resolvePreferencesWorker(),
                constants = resolveConstants()
        )
    }
}