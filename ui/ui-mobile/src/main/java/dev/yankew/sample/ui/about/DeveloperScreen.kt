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

package dev.yankew.sample.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yankew.sample.compose.collectAsStateWhenStarted
import dev.yankew.sample.strings.R
import dev.yankew.sample.ui.SubScreenScaffold
import dev.yankew.sample.ui.settings.SettingsSwitch

/**
 * Stateful composable of Developer Screen
 */
@Composable
fun DeveloperScreen(
    navigateUp: () -> Unit,
) {
    val viewModel = hiltViewModel<DeveloperViewModel>()
    val newFeatureEnabled = viewModel.newFeatureLocalEnabled.collectAsStateWhenStarted(false).value
    DeveloperScreen(
        navigateUp = navigateUp,
        newFeatureEnabled = newFeatureEnabled,
        setNewFeatureEnabled = viewModel::setNewFeatureLocalEnabled
    )
}

/**
 * Stateless composable of Developer Screen
 */
@Composable
fun DeveloperScreen(
    navigateUp: () -> Unit,
    newFeatureEnabled: Boolean,
    setNewFeatureEnabled: (Boolean) -> Unit
) {
    SubScreenScaffold(
        titleResId = R.string.developer_title,
        navigateUp = navigateUp,
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .verticalScroll(state = rememberScrollState())
        ) {
            SettingsSwitch(
                title = stringResource(R.string.enable_new_feature),
                checked = newFeatureEnabled,
                onCheckedChange = setNewFeatureEnabled
            )
        }
    }
}
