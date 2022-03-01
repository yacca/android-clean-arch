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

package dev.yankew.sample.cleanarch.crashlytics

import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.yankew.sample.cleanarch.BuildConfig
import dev.yankew.sample.domain.event.CrashlyticsOptionEvent
import dev.yankew.sample.domain.event.EventHandler
import dev.yankew.sample.domain.settings.CrashlyticsOption
import dev.yankew.sample.domain.settings.SettingOption
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class CrashlyticsHelper @Inject constructor(
    @CrashlyticsOption private val crashlyticsOption: SettingOption<Boolean>,
) {
    val crashlyticsOptionHandler = EventHandler<CrashlyticsOptionEvent> {
        Timber.i("CrashlyticsOptionEvent received by CrashlyticsHelper")
        setUpCrashlytics()
    }

    suspend fun setUpCrashlytics() {
        if (BuildConfig.BUILD_TYPE == "release") {
            val enabled = crashlyticsOption.getValue().first()
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(enabled)
        }
    }
}
