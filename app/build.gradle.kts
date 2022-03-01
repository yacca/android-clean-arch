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
    id("common.base-android-app")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    defaultConfig {
        applicationId = "dev.yankew.cleanarch"
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":lib:init"))
    implementation(project(":lib:strings"))

    // Dependencies for injection
    debugImplementation(project(":comp:analytics-debug"))
    implementation(project(":comp:analytics-firebase"))
    implementation(project(":comp:config-firebase"))
    implementation(project(":comp:config-local"))
    implementation(project(":comp:core"))
    implementation(project(":comp:download"))
    implementation(project(":comp:feature"))
    implementation(project(":comp:notification"))
    implementation(project(":comp:settings"))
    implementation(project(":ui:ui-mobile"))
    implementation(project(":ui:ui-widget"))

    // Initialization
    // App Startup
    implementation(Libs.STARTUP)
    // Init WorkManager
    implementation(Libs.HILT_WORK)
    implementation(Libs.WORK_RUNTIME_KTX)

    // Firebase crashlytics
    implementation(platform(Libs.FIREBASE_BOM))
    implementation(Libs.FIREBASE_CRASHLYTICS)
}
