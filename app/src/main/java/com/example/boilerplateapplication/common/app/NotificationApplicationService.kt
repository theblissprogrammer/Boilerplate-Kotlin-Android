package com.example.boilerplateapplication.common.app

import com.example.appstores.sources.dependencies.HasDependencies
import com.example.boilerplateapplication.R
import com.example.coreandroid.sources.controls.ApplicationService
import com.example.coreandroid.sources.controls.PushNotificationApplicationServicable
import com.example.coreandroid.sources.protocols.AuthenticationServiceDelegate
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging.INSTANCE_ID_SCOPE
import com.example.boilerplateapplication.common.extensions.getString

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
class NotificationApplicationService: ApplicationService, HasDependencies,
        AuthenticationServiceDelegate, PushNotificationApplicationServicable {

    override fun authenticationDidLogin(userID: Int) {
        val token = FirebaseInstanceId.getInstance().getToken(
                getString(R.string.gcm_defaultSenderId), INSTANCE_ID_SCOPE)
    }

    override fun authenticationDidLogout(userID: Int) {
        FirebaseInstanceId.getInstance().deleteToken(
                getString(R.string.gcm_defaultSenderId), INSTANCE_ID_SCOPE)
    }

    override fun onTokenRefresh(deviceToken: String) {
    }
}