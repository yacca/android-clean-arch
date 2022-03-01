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

package dev.yankew.sample.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Blue700,
    primaryVariant = Blue900,
    onPrimary = Color.White,
    secondary = Blue700,
    secondaryVariant = Blue900,
    onSecondary = Color.White,
    error = Blue800,
    onBackground = Color.Black,

)

private val DarkThemeColors = darkColors(
    primary = Blue300,
    primaryVariant = Blue700,
    onPrimary = Color.Black,
    secondary = Blue300,
    onSecondary = Color.Black,
    error = Blue200,
    onBackground = Color.White
)

private val DemoLightThemeColors = lightColors(
    primary = Amber700,
    primaryVariant = Amber900,
    onPrimary = Color.White,
    secondary = Amber700,
    secondaryVariant = Amber900,
    onSecondary = Color.White,
    error = Amber800,
    onBackground = Color.Black,

)

private val DemoDarkThemeColors = darkColors(
    primary = Amber300,
    primaryVariant = Amber700,
    onPrimary = Color.Black,
    secondary = Amber300,
    onSecondary = Color.Black,
    error = Amber200,
    onBackground = Color.White
)

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isDemo: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (!isDemo) {
        if (darkTheme) DarkThemeColors else LightThemeColors
    } else {
        if (darkTheme) DemoDarkThemeColors else DemoLightThemeColors
    }
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun PreviewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MyTheme(darkTheme = darkTheme) {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
