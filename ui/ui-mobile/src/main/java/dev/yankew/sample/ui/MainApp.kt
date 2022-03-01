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

import androidx.activity.compose.BackHandler
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import dev.yankew.sample.compose.activityViewModel
import dev.yankew.sample.compose.theme.MyTheme
import dev.yankew.sample.domain.model.AppVariant
import dev.yankew.sample.ui.navigation.AppDrawer
import kotlinx.coroutines.launch

@Composable
fun MainApp() {
    val isDemo = activityViewModel<MainViewModel>().appVariant == AppVariant.MOBILE_DEMO
    MyTheme(isDemo = isDemo) {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val openDrawer: () -> Unit = { coroutineScope.launch { drawerState.open() } }
        val closeDrawer: () -> Unit = { coroutineScope.launch { drawerState.close() } }
        if (drawerState.isOpen) {
            BackHandler { closeDrawer() }
        }
        ModalDrawer(
            drawerContent = {
                AppDrawer(
                    navController = navController,
                    closeDrawer = closeDrawer
                )
            },
            drawerState = drawerState,
            gesturesEnabled = true
        ) {
            MainNavGraph(navController, openDrawer)
        }
    }
}
