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

package dev.yankew.sample.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yankew.sample.domain.settings.AnalyticsOption
import dev.yankew.sample.domain.settings.CrashlyticsOption
import dev.yankew.sample.domain.settings.NotificationOption
import dev.yankew.sample.domain.settings.SettingOption
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @AnalyticsOption private val analyticsOption: SettingOption<Boolean>,
    @CrashlyticsOption private val crashlyticsOption: SettingOption<Boolean>,
    @NotificationOption private val notificationOption: SettingOption<Boolean>,
) : ViewModel() {
    val analytics = analyticsOption.getValue()
    val crashlytics = crashlyticsOption.getValue()
    val notification = notificationOption.getValue()

    fun setAnalytics(enabled: Boolean) {
        viewModelScope.launch { analyticsOption.setValue(enabled) }
    }

    fun setCrashlytics(enabled: Boolean) {
        viewModelScope.launch { crashlyticsOption.setValue(enabled) }
    }

    fun setNotification(enabled: Boolean) {
        viewModelScope.launch { notificationOption.setValue(enabled) }
    }
}
