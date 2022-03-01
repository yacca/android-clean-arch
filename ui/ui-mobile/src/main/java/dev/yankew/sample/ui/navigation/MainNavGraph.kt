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

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.yankew.sample.ui.about.AboutScreen
import dev.yankew.sample.ui.about.DeveloperScreen
import dev.yankew.sample.ui.home.HomeScreen
import dev.yankew.sample.ui.navigation.NavDestinations
import dev.yankew.sample.ui.new.NewFeatureScreen
import dev.yankew.sample.ui.settings.SettingsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.ROUTE_HOME,
    ) {
        composable(route = NavDestinations.ROUTE_HOME) {
            HomeScreen(openDrawer)
        }
        composable(route = NavDestinations.ROUTE_NEW_FEATURE) {
            NewFeatureScreen(openDrawer)
        }
        composable(route = NavDestinations.ROUTE_SETTINGS) {
            SettingsScreen(openDrawer)
        }
        composable(route = NavDestinations.ROUTE_ABOUT) {
            AboutScreen(
                openDeveloper = { navController.navigate(NavDestinations.ROUTE_DEVELOPER) },
                openDrawer = openDrawer
            )
        }
        composable(route = NavDestinations.ROUTE_DEVELOPER) {
            DeveloperScreen { navController.navigateUp() }
        }
    }
}
