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

package dev.yankew.sample.config.firebase

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.tasks.await
import timber.log.Timber

@HiltWorker
class RemoteConfigFetchWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val remoteConfigHelper: RemoteConfigHelper,
) : CoroutineWorker(context, params) {
    companion object {
        private const val UNIQUE_WORK_NAME = "RemoteConfigFetch"

        fun enqueue(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val requestBuilder = OneTimeWorkRequestBuilder<RemoteConfigFetchWorker>()
                .setConstraints(constraints)
            WorkManager.getInstance(context).enqueueUniqueWork(
                UNIQUE_WORK_NAME,
                ExistingWorkPolicy.KEEP,
                requestBuilder.build()
            )
        }
    }

    override suspend fun doWork(): Result {
        return try {
            if (remoteConfigHelper.remoteConfig.fetchAndActivate().await()) {
                remoteConfigHelper.notifyConfigUpdated()
            }
            Result.success()
        } catch (e: Exception) {
            Timber.w("Fetch remote config failed: ${e.message}")
            Result.retry()
        }
    }
}
