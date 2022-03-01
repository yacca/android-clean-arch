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

package dev.yankew.sample.config.firebase

import android.content.Context
import dev.yankew.sample.init.ComponentInitializer
import timber.log.Timber

class RemoteConfigInitializer : ComponentInitializer<Unit>() {
    override fun create(context: Context) {
        Timber.i("Initialize Firebase Remote Config")
        RemoteConfigFetchWorker.enqueue(context)
    }
}
