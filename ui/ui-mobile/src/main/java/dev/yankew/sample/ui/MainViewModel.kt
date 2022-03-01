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

package dev.yankew.sample.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yankew.sample.domain.interactor.GetAppVariantUseCase
import dev.yankew.sample.domain.interactor.GetNewFeatureEnabledUseCase
import dev.yankew.sample.domain.model.successOr
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAppVariantUseCase: GetAppVariantUseCase,
    getNewFeatureEnabledUseCase: GetNewFeatureEnabledUseCase,
) : ViewModel() {
    val appVariant = getAppVariantUseCase()

    val newFeatureEnabled = getNewFeatureEnabledUseCase(Unit).map { it.successOr(false) }
}
