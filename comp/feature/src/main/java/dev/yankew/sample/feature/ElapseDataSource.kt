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

package dev.yankew.sample.feature

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yankew.sample.android.observeBroadcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart
import java.time.Instant
import javax.inject.Inject

interface ElapseDataSource {
    fun getElapsedSeconds(): Flow<Long>
    suspend fun saveLaunchTime()
}

class ElapseDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @FeatureDataStore private val dataStore: DataStore<Preferences>,
) : ElapseDataSource {
    override fun getElapsedSeconds(): Flow<Long> = IntentFilter().apply {
        addAction(Intent.ACTION_TIME_TICK)
    }.let { filter ->
        context.observeBroadcast(filter) { _, _ -> Instant.now().epochSecond }
    }.onStart {
        emit(Instant.now().epochSecond)
    }.combine(
        dataStore.data.mapNotNull { it[KEY_LAUNCH_EPOCH] }
    ) { now, launch -> now - launch }

    override suspend fun saveLaunchTime() {
        dataStore.edit {
            it[KEY_LAUNCH_EPOCH] = Instant.now().epochSecond
        }
    }

    companion object {
        private val KEY_LAUNCH_EPOCH = longPreferencesKey("launch_epoch")
    }
}
