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

class ClickTrackingEvent @Inject constructor(
    private val analytics: AnalyticsEventDispatcher,
) {
    fun logHelloClicked() = log(VALUE_BUTTON_HELLO)

    private fun log(button: String) =
        analytics.logEvent(NAME, PARAM_BUTTON_NAME to button)

    companion object {
        private const val NAME = "button_clicked"
        private const val PARAM_BUTTON_NAME = "button_name"
        private const val VALUE_BUTTON_HELLO = "hello_clicked"
    }
}
