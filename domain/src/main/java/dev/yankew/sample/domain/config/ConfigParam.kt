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

package dev.yankew.sample.domain.config

import dev.yankew.sample.domain.util.orNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Optional

fun interface ConfigParam<T> {
    fun getValue(): Flow<ConfigValue<T>>
}

interface LocalConfigParam<T> : ConfigParam<T> {
    suspend fun setValue(value: T)
}

fun interface RemoteConfigParam<T> : ConfigParam<T>

fun <T> Optional<out ConfigParam<T>>.getConfiguredValue(
    default: () -> Flow<T>
): Flow<T> {
    return orNull()?.getValue()?.value { default() } ?: default()
}

fun <T> Optional<out ConfigParam<T>>.getConfiguredValue(): Flow<ConfigValue<T>> {
    return orNull()?.getValue() ?: flowOf(ConfigValue.Unset)
}

fun <T> Optional<LocalConfigParam<T>>.getConfiguredValue(
    remote: Optional<RemoteConfigParam<T>>,
    default: () -> Flow<T>
): Flow<T> {
    return getConfiguredValue { remote.getConfiguredValue(default) }
}
