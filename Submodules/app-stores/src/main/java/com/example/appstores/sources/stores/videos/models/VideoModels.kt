package com.example.appstores.sources.stores.videos.models

/**
 * Created by ahmedsaad on 2018-03-09.
 * Copyright © 2018. All rights reserved.
 */
sealed class VideoModels {
    class YoutubeRequest(
            val channelId: String = "", // Default to empty
            val maxResults: Int = 50,
            val pageToken: String? = null
    ): VideoModels()

    class YoutubeSearchRequest(
            val query: String,
            val channelId: String = "", // Default to empty
            val maxResults: Int = 50,
            val pageToken: String? = null
    ): VideoModels()
}