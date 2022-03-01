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

import dev.yankew.sample.domain.coroutine.IoDispatcher
import dev.yankew.sample.domain.model.DataResult
import dev.yankew.sample.domain.repository.FeatureRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetElapsedMinutesUseCase @Inject constructor(
    private val featureRepository: FeatureRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Long>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<DataResult<Long>> {
        return featureRepository.getElapsedMinutes().map {
            DataResult.Success(it)
        }
    }
}
