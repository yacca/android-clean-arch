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

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import dev.yankew.sample.domain.config.ConfigValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

class RemoteConfigImpl @Inject constructor(
    private val remoteConfigHelper: RemoteConfigHelper
) : RemoteConfig {
    override operator fun <T> get(param: RemoteParam<T>): Flow<ConfigValue<T>> {
        return remoteConfigHelper.updateEvent.map { getValue(param) }
            .onStart { emit(getValue(param)) }
    }

    private fun <T> getValue(param: RemoteParam<T>): ConfigValue<T> {
        val value = remoteConfigHelper.remoteConfig[param.key]
        when (value.source) {
            FirebaseRemoteConfig.VALUE_SOURCE_STATIC -> return ConfigValue.Unset
            FirebaseRemoteConfig.VALUE_SOURCE_DEFAULT -> {
                Timber.i("The value of key <${param.key}> is retrieved from the defaults")
            }
            FirebaseRemoteConfig.VALUE_SOURCE_REMOTE -> {
                Timber.i("The value of key <${param.key}> is retrieved from Firebase Remote Config")
            }
        }
        @Suppress("UNCHECKED_CAST")
        return when (param) {
            is StringRemoteParam -> value.asString() as T
            is BooleanRemoteParam -> value.asBoolean() as T
            is LongRemoteParam -> value.asLong() as T
            is DoubleRemoteParam -> value.asDouble() as T
            is JsonRemoteParam -> Json.decodeFromString(param.serializer, value.asString())
        }.let { ConfigValue.ConfiguredValue(it) }
    }
}
