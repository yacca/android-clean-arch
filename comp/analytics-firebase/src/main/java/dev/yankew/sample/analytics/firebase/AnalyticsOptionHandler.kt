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
import com.google.firebase.ktx.Firebase
import dev.yankew.sample.domain.event.AnalyticsOptionEvent
import dev.yankew.sample.domain.event.EventHandler
import timber.log.Timber
import javax.inject.Inject

class AnalyticsOptionHandler @Inject constructor() : EventHandler<AnalyticsOptionEvent> {
    override suspend fun handle(event: AnalyticsOptionEvent) {
        Timber.i("AnalyticsOptionEvent received by AnalyticsOptionHandler")
        Firebase.analytics.setAnalyticsCollectionEnabled(event.enabled)
    }
}
