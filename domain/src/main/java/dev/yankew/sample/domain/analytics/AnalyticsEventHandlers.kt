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

import dev.yankew.sample.domain.event.AppLaunchedEvent
import dev.yankew.sample.domain.event.EventHandler
import dev.yankew.sample.domain.util.Logger
import javax.inject.Inject

class AnalyticsEventHandlers @Inject constructor(
    analyticsUserProperties: AnalyticsUserProperties,
    logger: Logger,
) {
    val appLaunchedHandler = EventHandler<AppLaunchedEvent> {
        logger.i("AppLaunchedEvent received by AnalyticsEventHandlers")
        analyticsUserProperties.setAppVariant(it.variant)
    }
}
