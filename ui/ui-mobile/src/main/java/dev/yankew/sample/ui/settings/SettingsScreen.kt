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

package dev.yankew.sample.ui.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yankew.sample.compose.theme.PreviewTheme
import dev.yankew.sample.strings.R
import dev.yankew.sample.ui.MainScreenScaffold

@Composable
fun SettingsScreen(
    openDrawer: () -> Unit,
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val analytics = viewModel.analytics.collectAsState(true).value
    val crashlytics = viewModel.crashlytics.collectAsState(true).value
    val notification = viewModel.notification.collectAsState(true).value
    SettingsScreen(
        notification = notification,
        setNotification = viewModel::setNotification,
        analytics = analytics,
        setAnalytics = viewModel::setAnalytics,
        crashlytics = crashlytics,
        setCrashlytics = viewModel::setCrashlytics,
        openDrawer = openDrawer
    )
}

@Composable
fun SettingsScreen(
    notification: Boolean,
    setNotification: (Boolean) -> Unit,
    analytics: Boolean,
    setAnalytics: (Boolean) -> Unit,
    crashlytics: Boolean,
    setCrashlytics: (Boolean) -> Unit,
    openDrawer: () -> Unit,
) {
    MainScreenScaffold(
        titleResId = R.string.settings_title,
        openDrawer = openDrawer
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .verticalScroll(state = rememberScrollState())
        ) {
            SettingsSwitch(
                title = stringResource(R.string.allow_notification),
                checked = notification,
                onCheckedChange = setNotification
            )
            SettingsSwitch(
                title = stringResource(R.string.allow_analytics),
                checked = analytics,
                onCheckedChange = setAnalytics
            )
            SettingsSwitch(
                title = stringResource(R.string.allow_crashlytics),
                checked = crashlytics,
                onCheckedChange = setCrashlytics
            )
        }
    }
}

@Preview("Settings screen")
@Preview("Settings screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    PreviewTheme {
        SettingsScreen(
            notification = true,
            setNotification = {},
            analytics = true,
            setAnalytics = {},
            crashlytics = true,
            setCrashlytics = {},
            openDrawer = {},
        )
    }
}
