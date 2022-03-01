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

import javax.inject.Inject

class SettingsChangedTrackingEvent @Inject constructor(
    private val analytics: AnalyticsEventDispatcher,
) {
    fun logAnalyticsChanged(enabled: Boolean) =
        log(if (enabled) VALUE_ANALYTICS_ENABLED else VALUE_ANALYTICS_DISABLED)

    fun logCrashlyticsChanged(enabled: Boolean) =
        log(if (enabled) VALUE_CRASHLYTICS_ENABLED else VALUE_CRASHLYTICS_DISABLED)

    fun logNotificationChanged(enabled: Boolean) =
        log(if (enabled) VALUE_NOTIFICATION_ENABLED else VALUE_NOTIFICATION_DISABLED)

    private fun log(value: String) = analytics.logEvent(EVENT_NAME, PARAM_SETTING_NAME to value)

    companion object {
        private const val EVENT_NAME = "settings_changed"
        private const val PARAM_SETTING_NAME = "setting_name"
        private const val VALUE_ANALYTICS_ENABLED = "analytics_enabled"
        private const val VALUE_ANALYTICS_DISABLED = "analytics_disabled"
        private const val VALUE_CRASHLYTICS_ENABLED = "crashlytics_enabled"
        private const val VALUE_CRASHLYTICS_DISABLED = "crashlytics_disabled"
        private const val VALUE_NOTIFICATION_ENABLED = "notification_enabled"
        private const val VALUE_NOTIFICATION_DISABLED = "notification_disabled"
    }
}
