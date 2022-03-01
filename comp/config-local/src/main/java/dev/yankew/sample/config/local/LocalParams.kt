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

package dev.yankew.sample.config.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dev.yankew.sample.domain.config.ConfigValue
import dev.yankew.sample.domain.config.NewFeatureLocalParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalParams @Inject constructor(
    @LocalConfigDataStore private val dataStore: DataStore<Preferences>,
) {
    val newFeatureParam = object : NewFeatureLocalParam {
        override fun getValue(): Flow<ConfigValue<Boolean>> = dataStore.data.map { preferences ->
            preferences[KEY_NEW_FEATURE_ENABLED]?.let { enabled ->
                ConfigValue.ConfiguredValue(enabled)
            } ?: ConfigValue.Unset
        }

        override suspend fun setValue(value: Boolean) {
            dataStore.edit { it[KEY_NEW_FEATURE_ENABLED] = value }
        }
    }

    companion object {
        private val KEY_NEW_FEATURE_ENABLED = booleanPreferencesKey("new_feature_enabled")
    }
}
