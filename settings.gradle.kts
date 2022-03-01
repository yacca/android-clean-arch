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

include(":app")
include(":apps:demo-mobile")
include(":comp:analytics-debug")
include(":comp:analytics-firebase")
include(":comp:config-firebase")
include(":comp:config-local")
include(":comp:core")
include(":comp:download")
include(":comp:feature")
include(":comp:notification")
include(":comp:sample-data")
include(":comp:settings")
include(":depconstraints")
include(":domain")
include(":lib:android")
include(":lib:compose")
include(":lib:init")
include(":lib:strings")
include(":ui:ui-mobile")
include(":ui:ui-widget")

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-clean-arch"
