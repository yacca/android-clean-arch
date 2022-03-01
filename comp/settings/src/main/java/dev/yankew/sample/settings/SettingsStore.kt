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

package dev.yankew.sample.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dev.yankew.sample.domain.analytics.SettingsChangedTrackingEvent
import dev.yankew.sample.domain.event.AnalyticsOptionEvent
import dev.yankew.sample.domain.event.CrashlyticsOptionEvent
import dev.yankew.sample.domain.event.EventDispatcher
import dev.yankew.sample.domain.settings.SettingOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsStore @Inject constructor(
    @SettingsDataStore private val dataStore: DataStore<Preferences>,
    analyticsOptionEventDispatcher: EventDispatcher<AnalyticsOptionEvent>,
    crashlyticsOptionEventDispatcher: EventDispatcher<CrashlyticsOptionEvent>,
    settingsChangedTrackingEvent: SettingsChangedTrackingEvent,
) {
    val analyticsOption = getBooleanOption(KEY_ANALYTICS, true) {
        // Only 'enabled event' can be logged
        settingsChangedTrackingEvent.logAnalyticsChanged(it)
        analyticsOptionEventDispatcher.dispatch(AnalyticsOptionEvent(it))
    }
    val crashlyticsOption = getBooleanOption(KEY_CRASHLYTICS, true) {
        settingsChangedTrackingEvent.logCrashlyticsChanged(it)
        crashlyticsOptionEventDispatcher.dispatch(CrashlyticsOptionEvent(it))
    }
    val notificationOption = getBooleanOption(KEY_NOTIFICATION, true) {
        settingsChangedTrackingEvent.logNotificationChanged(it)
    }

    private fun getBooleanOption(
        key: String,
        default: Boolean,
        dispatch: suspend (Boolean) -> Unit = {}
    ) = object : SettingOption<Boolean> {
        override fun getValue(): Flow<Boolean> {
            return dataStore.data.map { it[booleanPreferencesKey(key)] ?: default }
        }

        override suspend fun setValue(value: Boolean) {
            dataStore.edit { it[booleanPreferencesKey(key)] = value }
            dispatch(value)
        }
    }

    private companion object {
        const val KEY_NOTIFICATION = "notification"
        const val KEY_ANALYTICS = "analytics"
        const val KEY_CRASHLYTICS = "crashlytics"
    }
}
