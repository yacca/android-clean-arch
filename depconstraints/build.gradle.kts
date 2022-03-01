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

plugins {
    id("java-platform")
}

val activity = "1.4.0"
val annotation = "1.3.0"
val compose = Versions.COMPOSE
val coroutines = "1.6.0"
val dataStore = "1.0.0"
val firebaseBom = "29.1.0"
val hilt = Versions.HILT
val hiltJetPack = "1.0.0"
val lifecycle = "2.4.1"
val navigation = "2.4.1"
val serialization = "1.3.2"
val startup = "1.1.1"
val timber = "5.0.1"
val workManager = "2.7.1"

dependencies {
    constraints {
        api("${Libs.ACTIVITY_COMPOSE}:$activity")
        api("${Libs.ACTIVITY_KTX}:$activity")
        api("${Libs.ANNOTATION}:$annotation")
        api("${Libs.COMPOSE_ANIMATION}:$compose")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_RUNTIME}:$compose")
        api("${Libs.COMPOSE_THEME_ADAPTER}:$compose")
        api("${Libs.COMPOSE_TOOLING}:$compose")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COROUTINES}:$coroutines")
        api("${Libs.COROUTINES_PLAY_SERVICE}:$coroutines")
        api("${Libs.DATA_STORE_PREFERENCES}:$dataStore")
        api("${Libs.FIREBASE_BOM}:$firebaseBom")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_ANDROID_COMPILER}:$hilt")
        api("${Libs.HILT_ANDROIDX_COMPILER}:$hiltJetPack")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.HILT_CORE}:$hilt")
        api("${Libs.HILT_NAVIGATION_COMPOSE}:$hiltJetPack")
        api("${Libs.HILT_WORK}:$hiltJetPack")
        api("${Libs.KOTLIN_STDLIB}:${Versions.KOTLIN}")
        api("${Libs.KOTLINX_SERIALIZATION}:$serialization")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEWMODEL_COMPOSE}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEWMODEL_KTX}:$lifecycle")
        api("${Libs.NAVIGATION_COMPOSE}:$navigation")
        api("${Libs.STARTUP}:$startup")
        api("${Libs.TIMBER}:$timber")
        api("${Libs.WORK_RUNTIME_KTX}:$workManager")
    }
}
