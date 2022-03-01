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

package dev.yankew.sample.domain.analytics

import dev.yankew.sample.domain.model.AppVariant
import javax.inject.Inject

class AnalyticsUserProperties @Inject constructor(
    private val trackers: AnalyticsTrackers,
) {
    suspend fun setAppVariant(variant: AppVariant) {
        setProperties(PARAM_APP_VARIANT to variant.toString())
    }

    private suspend fun setProperties(vararg properties: Pair<String, String>) {
        trackers.filter { it.isAnalyticsEnabled() }.forEach {
            properties.forEach { (key, value) ->
                it.setUserProperty(key, value)
            }
        }
    }

    companion object {
        private const val PARAM_APP_VARIANT = "app_variant"
    }
}
