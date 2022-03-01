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

package common

import Libs
import Versions

plugins {
    id("common.base-android-library")
    kotlin("kapt")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(Libs.ACTIVITY_KTX)

    // Jetpack Compose
    implementation(Libs.COMPOSE_ANIMATION)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_RUNTIME)
    implementation(Libs.COMPOSE_TOOLING)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.HILT_NAVIGATION_COMPOSE)
    implementation(Libs.LIFECYCLE_RUNTIME_KTX)
    implementation(Libs.LIFECYCLE_VIEWMODEL_COMPOSE)
    implementation(Libs.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Libs.NAVIGATION_COMPOSE)
}
