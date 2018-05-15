package com.example.coreandroid.sources.data

import com.example.coreandroid.sources.enums.DefaultsKeys.Companion.userID
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.data.models.SyncActivity
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*


/**
 * Created by ahmedsaad on 2017-12-01.
 * Copyright © 2017. All rights reserved.
 */
class DataRealmStore(override val preferencesWorker: PreferencesWorkerType): DataStore {

    init {
        this.configure()
    }

    private val isAuthenticated: Boolean
        get() {
            return preferencesWorker.get(userID) ?: 0 > 0
        }

    override fun configure() {
        val realmFileName = "$name.realm"

        // Skip if already set up before
        if (Realm.getDefaultConfiguration()?.realmFileName != realmFileName) {
            val config = RealmConfiguration.Builder()
                    .name(realmFileName)
                    .deleteRealmIfMigrationNeeded()
                    .compactOnLaunch { totalBytes, usedBytes ->
                        // Compact if the file is over X MB in size and less than 50% 'used'
                        // https://realm.io/docs/swift/latest/#compacting-realms
                        val maxSize = 100 * 1024 * 1024 //100MB
                        val shouldCompact = (totalBytes > maxSize) && (Double.fromBits(usedBytes) / Double.fromBits(totalBytes)) < 0.5

                        if (shouldCompact) {
                            // Log should compact
                            LogHelper.w(messages = *arrayOf("Compacting Realm database."))
                        }

                        shouldCompact
                    }
                    .build()

            // Set the configuration used for user's Realm
            Realm.setDefaultConfiguration(config)

            // Attempt to initialize and clean if necessary
            try {
                val realm = Realm.getDefaultInstance()
                realm.close()
            } catch (exception: Exception) {
                LogHelper.e(messages = *arrayOf("Could not initialize Realm database: " +
                        "${exception.localizedMessage}. Deleting database and recreating..."))

                delete(preferencesWorker.get(userID) ?: 0)
            }

            LogHelper.i(messages = *arrayOf("Realm database configured for $name"))
        }
    }

    override fun delete(userID: Int) {
        val realmFileName = "${generateName(userID)}.realm"
        val realmConfiguration: RealmConfiguration?  = RealmConfiguration.Builder()
                .name(realmFileName)
                .build()

        if (realmConfiguration != null)
            Realm.deleteRealm(realmConfiguration)

    }



    /// Get sync activity for type.
    private fun getSyncActivityLastPulledAt(typeName: String, suffix: String = ""): Date? {
        val realm: Realm = Realm.getDefaultInstance()
        return try {
            // Build the query looking at all users:
            val query = realm.where(SyncActivity::class.java)

            // Add query conditions:
            query.equalTo("type", "$typeName $suffix")

            query.findFirst()?.lastPulledAt
        } catch (error: Exception) {
            LogHelper.e(messages = *arrayOf("Could not initialize database: ${error.localizedMessage}"))
            null
        } finally {
            realm.close()
        }
    }

    /// Update or create last pulled at date for sync activity.
    private fun updateSyncActivity(typeName: String, lastPulledAt: Date, suffix: String = "") {
        val realm = Realm.getDefaultInstance()

        try {
            val syncActivity = SyncActivity(
                    type = "$typeName $suffix",
                    lastPulledAt = lastPulledAt)

            realm.executeTransaction {
                realm.insertOrUpdate(syncActivity)
            }

        } catch (error: Exception)  {
            LogHelper.e(messages = *arrayOf("Could not write sync activity to Realm for $typeName: ${error.localizedMessage}"))
        } finally {
            realm.close()
        }
    }

}