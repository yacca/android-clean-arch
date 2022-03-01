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

package dev.yankew.sample.ui.navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.yankew.sample.compose.activityViewModel
import dev.yankew.sample.compose.collectAsStateWhenStarted
import dev.yankew.sample.compose.theme.PreviewTheme
import dev.yankew.sample.strings.R
import dev.yankew.sample.ui.MainViewModel
import dev.yankew.sample.ui.navigation.NavDestinations.ROUTE_ABOUT
import dev.yankew.sample.ui.navigation.NavDestinations.ROUTE_HOME
import dev.yankew.sample.ui.navigation.NavDestinations.ROUTE_NEW_FEATURE
import dev.yankew.sample.ui.navigation.NavDestinations.ROUTE_SETTINGS

@Composable
fun AppDrawer(
    navController: NavController,
    closeDrawer: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ROUTE_HOME
    val newFeatureEnabled = activityViewModel<MainViewModel>().newFeatureEnabled
        .collectAsStateWhenStarted(false).value
    AppDrawer(
        currentRoute = currentRoute,
        newFeatureEnabled = newFeatureEnabled,
        navController = navController,
        closeDrawer = closeDrawer
    )
}

@Composable
fun AppDrawer(
    currentRoute: String,
    newFeatureEnabled: Boolean,
    navController: NavController,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val onEntrySelected: (String) -> Unit = {
        closeDrawer()
        if (currentRoute != it) {
            navController.navigate(it) {
                launchSingleTop = true
                restoreState = true
                popUpTo(ROUTE_HOME) {
                    saveState = true
                }
            }
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        MainEntry.values().filter {
            newFeatureEnabled || it != MainEntry.NEW_FEATURE
        }.forEach { entry ->
            DrawerItem(
                icon = entry.icon,
                label = stringResource(entry.labelResId),
                isSelected = currentRoute == entry.route,
                action = { onEntrySelected(entry.route) }
            )
        }
    }
}

@Composable
private fun DrawerItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    imageVector = icon,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(textIconColor),
                    alpha = if (isSelected) 1f else 0.6f
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}

private enum class MainEntry(
    @StringRes val labelResId: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home_title, Icons.Filled.Home, ROUTE_HOME),
    NEW_FEATURE(R.string.new_feature_title, Icons.Filled.Star, ROUTE_NEW_FEATURE),
    SETTINGS(R.string.settings_title, Icons.Filled.Settings, ROUTE_SETTINGS),
    ABOUT(R.string.about_title, Icons.Filled.Info, ROUTE_ABOUT)
}

@Preview("App Drawer")
@Preview("App Drawer (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    PreviewTheme {
        AppDrawer(
            currentRoute = ROUTE_HOME,
            newFeatureEnabled = true,
            navController = rememberNavController(),
            closeDrawer = { }
        )
    }
}
