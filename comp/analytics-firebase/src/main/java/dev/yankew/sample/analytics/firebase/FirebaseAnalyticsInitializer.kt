/*
 * Copyright 2022 WANG Yanke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.yankew.sample.analytics.firebase

import android.content.Context
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import dev.yankew.sample.domain.coroutine.ApplicationScope
import dev.yankew.sample.init.ComponentInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class FirebaseAnalyticsInitializer : ComponentInitializer<Unit>() {
    @Inject
    lateinit var firebaseAnalyticsTracker: FirebaseAnalyticsTracker

    @Inject
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    override fun create(context: Context) {
        Timber.i("Initialize Firebase Analytics")
        FirebaseAnalyticsInitializerEntryPoint.resolve(context).inject(this)
        applicationScope.launch {
            val enabled = firebaseAnalyticsTracker.isAnalyticsEnabled()
            Firebase.analytics.setAnalyticsCollectionEnabled(enabled)
        }
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FirebaseAnalyticsInitializerEntryPoint {
    fun inject(initializer: FirebaseAnalyticsInitializer)

    companion object {
        fun resolve(context: Context): FirebaseAnalyticsInitializerEntryPoint {
            val appContext = context.applicationContext ?: throw IllegalStateException()
            return EntryPointAccessors.fromApplication(
                appContext,
                FirebaseAnalyticsInitializerEntryPoint::class.java
            )
        }
    }
}
