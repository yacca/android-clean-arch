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

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yankew.sample.cleanarch.crashlytics.CrashlyticsHelper
import dev.yankew.sample.cleanarch.crashlytics.CrashlyticsTree
import dev.yankew.sample.domain.coroutine.ApplicationScope
import dev.yankew.sample.init.AppBaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AppBaseInitializerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val workerFactory: HiltWorkerFactory,
    private val crashlyticsHelper: CrashlyticsHelper,
) : AppBaseInitializer {
    override fun init() {
        if (BuildConfig.BUILD_TYPE == "release") {
            Timber.plant(CrashlyticsTree())
        } else {
            Timber.plant(CrashlyticsTree(), Timber.DebugTree())
        }
        Timber.i("App base initialization")

        applicationScope.launch {
            crashlyticsHelper.setUpCrashlytics()
        }

        WorkManager.initialize(
            context,
            Configuration.Builder().setWorkerFactory(workerFactory).build()
        )
    }
}
