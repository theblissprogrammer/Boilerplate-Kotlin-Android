package com.example.appstores.sources.dependencies

import com.example.appstores.sources.stores.videos.VideosNetworkStore
import com.example.appstores.sources.stores.videos.VideosStore
import com.example.appstores.sources.stores.videos.VideosWorker
import com.example.appstores.sources.stores.videos.VideosWorkerType
import com.example.coreandroid.sources.dependencies.CoreDependency

open class StoreDependency: CoreDependency(), StoreDependable {

    override fun resolveVideosWorker(): VideosWorkerType {
        return VideosWorker(
                store = resolveVideosNetworkStore()
        )
    }

    override fun resolveVideosNetworkStore(): VideosStore {
        return VideosNetworkStore(
                httpService = resolveHTTPService(),
                constants = resolveConstants()
        )
    }
}