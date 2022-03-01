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

import kotlinx.serialization.KSerializer

sealed class RemoteParam<T>(open val key: String)

data class StringRemoteParam(override val key: String) : RemoteParam<String>(key)

data class BooleanRemoteParam(override val key: String) : RemoteParam<Boolean>(key)

data class LongRemoteParam(override val key: String) : RemoteParam<Long>(key)

data class DoubleRemoteParam(override val key: String) : RemoteParam<Double>(key)

data class JsonRemoteParam<T>(
    override val key: String,
    val serializer: KSerializer<T>
) : RemoteParam<T>(key)
