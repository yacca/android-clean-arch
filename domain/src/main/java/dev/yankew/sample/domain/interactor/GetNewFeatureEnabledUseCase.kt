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

package dev.yankew.sample.domain.interactor

import dev.yankew.sample.domain.config.NewFeatureLocalParam
import dev.yankew.sample.domain.config.NewFeatureParam
import dev.yankew.sample.domain.config.NewFeatureRemoteParam
import dev.yankew.sample.domain.config.getConfiguredValue
import dev.yankew.sample.domain.coroutine.IoDispatcher
import dev.yankew.sample.domain.model.DataResult
import dev.yankew.sample.domain.util.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.util.Optional
import javax.inject.Inject

class GetNewFeatureEnabledUseCase @Inject constructor(
    @NewFeatureParam private val localParam: Optional<NewFeatureLocalParam>,
    @NewFeatureParam private val remoteParam: Optional<NewFeatureRemoteParam>,
    private val logger: Logger,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Boolean>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<DataResult<Boolean>> {
        return localParam.getConfiguredValue(remoteParam) {
            logger.i("Use local default value for new feature enabled")
            flowOf(false)
        }.map { DataResult.Success(it) }
    }
}
