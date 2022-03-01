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

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yankew.sample.compose.collectAsStateWhenStarted
import dev.yankew.sample.compose.theme.PreviewTheme
import dev.yankew.sample.strings.R
import dev.yankew.sample.ui.MainScreenScaffold

/**
 * Stateful composable of Home Screen
 */
@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val elapsedMinutes = viewModel.elapsedMinutes.collectAsStateWhenStarted(-1).value
    HomeScreen(
        elapsedMinutes = elapsedMinutes,
        onButtonClicked = viewModel::logHello,
        openDrawer = openDrawer
    )
}

/**
 * Stateless composable of Home Screen
 */
@Composable
fun HomeScreen(
    elapsedMinutes: Long,
    onButtonClicked: () -> Unit,
    openDrawer: () -> Unit,
) {
    MainScreenScaffold(titleResId = R.string.home_title, openDrawer = openDrawer) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (elapsedMinutes >= 0) {
                Text(stringResource(R.string.elapsed, elapsedMinutes))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onButtonClicked) {
                Text(stringResource(R.string.button_hello))
            }
        }
    }
}

@Preview("Home screen")
@Preview("Home screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    PreviewTheme {
        HomeScreen(
            elapsedMinutes = 10L,
            onButtonClicked = {},
            openDrawer = {},
        )
    }
}
