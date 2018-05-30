package com.example.appstores.sources.network

import android.net.Uri
import com.example.appstores.sources.preferences.baseRESTPath
import com.example.appstores.sources.preferences.baseURL
import com.example.coreandroid.sources.network.APIRoutable
import com.example.coreandroid.sources.preferences.ConstantsType

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

sealed class APIRouter: APIRoutable() {

    override fun getURI(constants: ConstantsType) = Uri.parse(constants.baseURL)
            .buildUpon()
            .appendEncodedPath(constants.baseRESTPath)
            .appendEncodedPath(path)
}