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

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yankew.sample.domain.config.NewFeatureParam

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigModule {
    @Provides
    @NewFeatureParam
    fun newFeatureParam(remoteParams: RemoteParams) = remoteParams.newFeatureParam
}

@Module
@InstallIn(SingletonComponent::class)
interface RemoteConfigInterfaceModule {
    @Binds
    @Reusable
    fun remoteConfig(remoteConfigImpl: RemoteConfigImpl): RemoteConfig
}
