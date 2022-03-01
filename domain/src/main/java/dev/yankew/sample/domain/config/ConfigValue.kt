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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

sealed class ConfigValue<out T> {
    data class ConfiguredValue<out T>(val data: T) : ConfigValue<T>()
    object Unset : ConfigValue<Nothing>()
}

fun <T> Flow<ConfigValue<T>>.value(default: () -> Flow<T>): Flow<T> =
    catch { default() }.flatMapLatest {
        when (it) {
            is ConfigValue.ConfiguredValue -> flowOf(it.data)
            is ConfigValue.Unset -> default()
        }
    }
