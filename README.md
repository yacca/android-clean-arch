# Android Clean Architecture Sample
[![Check](https://github.com/yacca/android-clean-arch/actions/workflows/Check.yaml/badge.svg)](https://github.com/yacca/android-clean-arch/actions/workflows/Check.yaml)

A sample project demonstrating clean architecture in an Android app

## Tech stack
- [Kotlin](https://kotlinlang.org/)
    - [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
    - [Flow](https://kotlinlang.org/docs/flow.html)
- [Jetpack](https://developer.android.com/jetpack)
    - [App Startup](https://developer.android.com/topic/libraries/app-startup)
    - [Compose](https://developer.android.com/jetpack/compose)
    - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
    - [Hilt](https://dagger.dev/hilt/)
    - [Hilt Android](https://developer.android.com/training/dependency-injection/hilt-android)
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    - [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
    - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
- [Firebase](https://firebase.google.com/)
    - [Analytics](https://firebase.google.com/products/analytics)
    - [Crashlytics](https://firebase.google.com/products/crashlytics)
    - [Remote Config](https://firebase.google.com/products/remote-config)
- Others
    - [Gradle](https://gradle.org/)
    - [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)
    - [PlantUML](https://plantuml.com/)
    - [Spotless](https://github.com/diffplug/spotless)
    - [Timber](https://github.com/JakeWharton/timber)

## Modules and their dependencies

All modules
![All modules](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/yacca/android-clean-arch/main/doc/all-modules.puml)

Modules of mobile app
![Modules of mobile app](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/yacca/android-clean-arch/main/doc/mobile-app-modules.puml)

Modules of demo mobile app
![Modules of demo mobile app](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/yacca/android-clean-arch/main/doc/mobile-demo-modules.puml)

## License
```
Copyright 2022 WANG Yanke

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
