package com.example.coreandroid.sources.logging

import android.content.Context
import android.os.Build
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.enums.DefaultsKeys.Companion.userID
import com.example.coreandroid.sources.enums.Environment
import com.example.coreandroid.sources.logging.destinations.LogDNADestination
import com.example.coreandroid.sources.preferences.ConstantsType
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import org.json.JSONObject
import java.lang.Math.sqrt

/**
 * Created by ahmedsaad on 2017-11-06.
 * Copyright © 2017. All rights reserved.
 */

object Logger: HasDependencies {

    private val preferencesWorker: PreferencesWorkerType by lazy {
        dependencies.resolvePreferencesWorker()
    }

    private val constants: ConstantsType by lazy {
        dependencies.resolveConstants()
    }

    private val context: Context? by lazy {
        dependencies.resolveContext()
    }

    val environment = Environment.mode

    private val systemVersion: String by lazy {
        "${Build.VERSION.SDK_INT} (${Build.VERSION.RELEASE})"
    }

    lateinit var version: String

    private val deviceModel: String by lazy {
        "${Build.MANUFACTURER} ${Build.BRAND} ${Build.MODEL}"
    }

    fun setupLogger(appVersion: String?, appBuild: Int?) {
        this.version = "${appVersion ?: "-"} (${appBuild ?: "-"})"
        setUpCloud()
    }

    private fun setUpCloud() {
        LogHelper.destinations.add(
                LogDNADestination(
                        ingestionKey = constants.logDNAKey,
                        hostName = "Android",
                        appName = context?.applicationInfo?.loadLabel(context?.packageManager).toString(),
                        environment = Environment.mode.name.toLowerCase().capitalize()
                )
        )

    }

    // Complete the consecutive function below.
    fun consecutive(num: Long): Int {
        var numberOfWays = 0
        var currentSum: Long = 0
        var initialLong:Long = 1
        var i: Long = 1
        while (i < num) {
            if (currentSum == 0L) initialLong = i

            currentSum += i

            if (currentSum > num) {
                currentSum = 0
                i = initialLong + 1
            }
            else if (currentSum == num) {
                currentSum = 0
                numberOfWays += 1
                i = initialLong + 1
            }

            i += 1
            println("current sum is $currentSum and number of ways is $numberOfWays")
        }

        return numberOfWays
    }

    fun countConsecutive(N: Int): Int {
        // constraint on values of L gives us the
        // time Complexity as O(N^0.5)
        var count = 0
        var M = 1.0
        while (M * M  < 2.0 * N) {
            val a = (N / M) + (M / 2) + 0.5
            if (a % 1.0 == 0.0)
                count++
            M++
        }
        return count
    }

    /// Meta data to append to the log
    val metaLog: JSONObject
        get() {
            val output = JSONObject()

            output.put("user_id", preferencesWorker.get(userID) ?: 0)
            output.put("app_version", version)
            output.put("system_version", systemVersion)
            output.put("device_model", deviceModel)
            output.put("environment", environment.name)

            // TODO: Add application state to metadata
            /*if let application = Logger.application {
                output["application_state"] = {
                    switch application.applicationState {
                        case .active: return "active"
                        case .background: return "background"
                        case .inactive: return "inactive"
                    }
                }()

                output["protected_data_available"] = application.isProtectedDataAvailable
            }*/

            return output
        }
}