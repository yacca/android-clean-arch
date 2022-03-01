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

package dev.yankew.sample.cleanarch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.yankew.sample.domain.coroutine.ApplicationScope
import dev.yankew.sample.domain.event.AppLaunchedEvent
import dev.yankew.sample.domain.event.EventDispatcher
import dev.yankew.sample.domain.model.AppVariant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
    @Inject
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    @Inject
    lateinit var eventDispatcher: EventDispatcher<AppLaunchedEvent>

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            eventDispatcher.dispatch(AppLaunchedEvent(AppVariant.MOBILE))
        }
    }
}
