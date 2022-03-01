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
}

android {
    defaultConfig {
        applicationId = "dev.yankew.cleanarch.demo"
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":lib:init"))
    implementation(project(":lib:strings"))

    // Dependencies for injection
    implementation(project(":comp:analytics-debug"))
    implementation(project(":comp:config-local"))
    implementation(project(":comp:core"))
    implementation(project(":comp:notification"))
    implementation(project(":comp:sample-data"))
    implementation(project(":comp:settings"))
    implementation(project(":ui:ui-mobile"))
    implementation(project(":ui:ui-widget"))
}
