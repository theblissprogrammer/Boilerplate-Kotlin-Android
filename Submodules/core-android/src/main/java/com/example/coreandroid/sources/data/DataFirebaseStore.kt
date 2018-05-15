package com.example.coreandroid.sources.data

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.example.coreandroid.sources.ConstantsType
import com.example.coreandroid.sources.common.CompletionResponse.Companion.failure
import com.example.coreandroid.sources.common.CompletionResponse.Companion.success
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.preferences.PreferencesWorkerType

/**
 * Created by ahmedsaad on 2018-02-08.
 * Copyright © 2017. All rights reserved.
 */
class DataFirebaseStore(override val preferencesWorker: PreferencesWorkerType,
                        val context: Context?,
                        val constants: ConstantsType): DataStore {

    init {
        this.configure()
    }

    override fun configure() {
        // Validate if configured before
        try { if (FirebaseApp.getInstance(name) != null) return } catch (e: Exception){}

        // Configure Firebase
        val options = FirebaseOptions.Builder()
                .setApiKey(constants.firebaseApiKey)
                .setApplicationId(constants.firebaseApplicationID)
                .setDatabaseUrl(constants.firebaseDatabaseUrl)
                .setProjectId(constants.firebaseProjectID)
                .setStorageBucket(constants.firebaseStorageBucket).build() ?: return LogHelper.e(
                messages = *arrayOf("Could not initialize Firebase for \"$name\"."))

        try {
            FirebaseApp.initializeApp(context, options, name)
        } catch (e: Exception) {
            LogHelper.e(messages = *arrayOf("An error occurred while initializing Firebase: ${e.localizedMessage}"))
        }

        LogHelper.d(messages = *arrayOf("Firebase database \"$name\" initialized with config: $options."))
    }

    override fun delete(userID: Int) {
        val currentName = generateName(userID)

        try {
            val firebaseApp = FirebaseApp.getInstance(currentName)
            // TODO: Delete firebase app for user
        } catch (e: Exception) {
            LogHelper.e(messages = *arrayOf("Could not initialize Firebase for \"$currentName\"."))
        }
    }

    fun signIn(token: String, completion: Result<Void, DataError>) {
        val auth = auth() ?: return completion(failure(DataError.DatabaseFailure(null)))

        auth.signInWithCustomToken(token).addOnCompleteListener {
            if (!it.isSuccessful || it.exception != null) {
                LogHelper.e(messages = *arrayOf("Could not sign into Firebase:" +
                        " ${it.exception?.localizedMessage})"))

                completion(failure(DataError.Unauthorized))
                return@addOnCompleteListener
            }

            LogHelper.i(messages = *arrayOf("Sign-in to Firebase succeeded for ${this.name}."))

            completion(success())
        }
    }

    fun signOut(userID: Int) {
        try {
            val firebaseApp = FirebaseApp.getInstance(generateName(userID))
            val auth = FirebaseAuth.getInstance(firebaseApp)

            try {
                auth.signOut()
            } catch (exception: Exception) {
                LogHelper.e(messages = *arrayOf("Could not sign out of " +
                        "Firebase: ${exception.localizedMessage}"))
            }

            // TODO: Bug in Firebase delete, uncomment when fixed
            // https://github.com/firebase/firebase-ios-sdk/issues/683
            // delete(userID)

            LogHelper.i(messages = *arrayOf("Sign-out of Firebase succeeded for user #$userID."))
        } catch (e: Exception) {
            LogHelper.e(messages = *arrayOf("Could not instantiate Firebase App for user #$userID."))
        }
    }

    private fun auth(): FirebaseAuth? {
        // Ensure Firebase configured for correct user before use
        configure()

        return try {
            val firebaseApp = FirebaseApp.getInstance(name)
            FirebaseAuth.getInstance(firebaseApp)
        } catch (e: Exception) {
            LogHelper.e(messages = *arrayOf("Could not instantiate Firebase App for $name."))
            null
        }
    }

    fun database(): FirebaseFirestore? {
        // Ensure Firebase configured for correct user before use
        configure()

        return try {
            val firebaseApp = FirebaseApp.getInstance(name)
            FirebaseFirestore.getInstance(firebaseApp)
        } catch (e: Exception) {
            LogHelper.e(messages = *arrayOf("Could not instantiate Firebase App for $name."))
            null
        }
    }

    fun currentUser(): FirebaseUser? {
        val auth = auth()

        if(auth == null) {
            LogHelper.e(messages = *arrayOf("Could not instantiate Firebase App for $name."))
            return null
        }

        return auth.currentUser
    }

}