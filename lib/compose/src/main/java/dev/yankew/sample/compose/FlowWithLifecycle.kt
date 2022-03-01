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

package dev.yankew.sample.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 *  Collect the flow as Compose State when the lifecycle is at least at STARTED state.
 *
 *  @param initial The initial value of the returned Compose State
 */
@Composable
fun <T : R, R> Flow<T>.collectAsStateWhenStarted(
    initial: R
): State<R> = collectAsStateWhenStarted { initial }

/**
 *  Collect the flow as Compose State when the lifecycle is at least at STARTED state.
 *
 *  No initial parameter is required here because StateFlow has.
 */
@Composable
fun <T> StateFlow<T>.collectAsStateWhenStarted(): State<T> = collectAsStateWhenStarted { value }

@Composable
private fun <T : R, R> Flow<T>.collectAsStateWhenStarted(initial: () -> R): State<R> {
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
    val state = remember(this) { mutableStateOf(initial()) }
    LaunchedEffect(this) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect { state.value = it }
        }
    }
    return state
}
