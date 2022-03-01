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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yankew.sample.strings.R
import dev.yankew.sample.ui.MainScreenScaffold

/**
 * Stateful composable of About Screen
 */
@Composable
fun AboutScreen(
    openDeveloper: () -> Unit,
    openDrawer: () -> Unit,
) {
    AboutScreen(
        hasDeveloper = hiltViewModel<AboutViewModel>().isLocalConfigPresent,
        openDeveloper = openDeveloper,
        openDrawer = openDrawer
    )
}

/**
 * Stateless composable of About Screen
 */
@Composable
fun AboutScreen(
    hasDeveloper: Boolean,
    openDeveloper: () -> Unit,
    openDrawer: () -> Unit,
) {
    MainScreenScaffold(
        titleResId = R.string.about_title,
        openDrawer = openDrawer,
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.about_text))
            if (hasDeveloper) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = openDeveloper) {
                    Text(stringResource(R.string.button_developer))
                }
            }
        }
    }
}
