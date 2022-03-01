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

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dev.yankew.sample.domain.analytics.AnalyticsTracker
import dev.yankew.sample.domain.settings.AnalyticsOption
import dev.yankew.sample.domain.settings.SettingOption
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Provider

class FirebaseAnalyticsTracker @Inject constructor(
    @AnalyticsOption private val analyticsOption: Provider<SettingOption<Boolean>>,
) : AnalyticsTracker {
    override suspend fun isAnalyticsEnabled(): Boolean {
        return analyticsOption.get().getValue().first()
    }

    override fun logEvent(name: String, params: Map<String, Any>) {
        Firebase.analytics.logEvent(name) {
            params.forEach { (key, value) ->
                when (value) {
                    is String -> param(key, value)
                    is Int -> param(key, value.toLong())
                    is Long -> param(key, value)
                    else -> param(key, value.toString())
                }
            }
        }
    }

    override fun setUserProperty(key: String, value: String) {
        Firebase.analytics.setUserProperty(key, value)
    }
}
