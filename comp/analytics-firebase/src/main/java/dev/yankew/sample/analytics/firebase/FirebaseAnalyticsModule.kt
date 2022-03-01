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

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.yankew.sample.domain.analytics.AnalyticsTracker
import dev.yankew.sample.domain.event.AnalyticsOptionEvent
import dev.yankew.sample.domain.event.EventHandler

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseAnalyticsModule {
    @Binds
    @IntoSet
    fun analyticsOptionHandler(handler: AnalyticsOptionHandler): EventHandler<AnalyticsOptionEvent>

    @Binds
    @IntoSet
    fun firebaseAnalyticsTracker(tracker: FirebaseAnalyticsTracker): AnalyticsTracker
}
