package com.example.coreandroid.sources.network

import android.net.Uri
import com.example.coreandroid.sources.enums.NetworkMethod
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

abstract class APIRoutable {
    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    abstract val method: NetworkMethod
    abstract val path: String
    open val requestJSON: JSONObject? = null
    open val requestBody: RequestBody? = null
    open val queryParameterList: MutableMap<String, Any>? = null

    fun asURLRequest(preferencesWorker: PreferencesWorkerType) : Request.Builder {
        val uri = Uri.parse(preferencesWorker.baseURL)
                .buildUpon()
                .appendEncodedPath(preferencesWorker.baseUserREST)
                .appendEncodedPath(path)

        queryParameterList?.forEach { uri.appendQueryParameter(it.key, it.value.toString()) }

        val requestBody =  requestBody ?: if (requestJSON != null)
            RequestBody.create(JSON, requestJSON.toString()) else
            RequestBody.create(null, "")

        return Request.Builder()
                .url(uri.build().toString())
                .method(method.name, requestBody)
    }
}