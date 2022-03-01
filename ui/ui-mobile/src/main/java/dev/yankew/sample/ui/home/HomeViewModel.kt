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

package dev.yankew.sample.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yankew.sample.domain.analytics.ClickTrackingEvent
import dev.yankew.sample.domain.interactor.GetElapsedMinutesUseCase
import dev.yankew.sample.domain.model.data
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getElapsedMinutesUseCase: GetElapsedMinutesUseCase,
    private val clickTrackingEvent: ClickTrackingEvent
) : ViewModel() {
    val elapsedMinutes = getElapsedMinutesUseCase(Unit).mapNotNull { it.data }

    fun logHello() {
        clickTrackingEvent.logHelloClicked()
    }
}
