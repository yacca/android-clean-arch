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

package dev.yankew.sample.analytics.debug

import dev.yankew.sample.domain.analytics.AnalyticsTracker
import dev.yankew.sample.domain.settings.AnalyticsOption
import dev.yankew.sample.domain.settings.SettingOption
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class DebugAnalyticsTracker @Inject constructor(
    @AnalyticsOption private val analyticsOption: Provider<SettingOption<Boolean>>,
) : AnalyticsTracker {
    private val userProperties: MutableMap<String, String> = mutableMapOf()

    override suspend fun isAnalyticsEnabled(): Boolean {
        return analyticsOption.get().getValue().first()
    }

    override fun logEvent(name: String, params: Map<String, Any>) {
        val eventNameString = "Event Name: $name"
        val title = " Debug Analytics "
        val maxKeyLen = params.keys.maxOfOrNull { it.length } ?: 4
        val maxValueLen = params.values.maxOfOrNull { it.toString().length } ?: 4
        val dividerLineLen = (maxKeyLen + maxValueLen + 4).coerceAtLeast(eventNameString.length)
        val dividerLen = ((dividerLineLen - title.length + 1) / 2).coerceAtLeast(4)
        val parameters = params.toString(maxKeyLen)
        val maxPropertyKeyLen = userProperties.keys.maxOfOrNull { it.length } ?: 4
        val properties = userProperties.toString(maxPropertyKeyLen)
        val topDivider = "=".repeat(dividerLen)
        val bottomDivider = "-".repeat(dividerLen)
        Timber.v(
            """
            ||
            |$topDivider$title$topDivider
            |$eventNameString
            |Params:
            |$parameters
            |User Properties:
            |$properties
            |$bottomDivider$title$bottomDivider
            ||
            """.trimMargin()
        )
    }

    override fun setUserProperty(key: String, value: String) {
        userProperties[key] = value
    }

    private fun Map<String, Any>.toString(maxKeyLen: Int): String {
        val keyFormat = "%-${maxKeyLen}s"
        return entries.joinToString("\n") {
            val keyString = keyFormat.format(it.key)
            "  $keyString: ${it.value}"
        }
    }
}
