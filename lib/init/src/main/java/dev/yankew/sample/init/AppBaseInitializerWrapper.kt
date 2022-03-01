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

package dev.yankew.sample.init

import android.content.Context
import androidx.startup.Initializer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import java.util.Optional

/**
 * This class is used to overcome the limitation of App Startup: the dependencies should be known
 * before create() method and the dependencies can't be provided by injection in create().
 * The app module can implement AppBaseInitializer interface to do some basic initialization before
 * all others. And feature modules can depend this initializer wrapper to make sure their
 * initializations happen after the base initialization.
 */
class AppBaseInitializerWrapper : Initializer<Unit> {
    override fun create(context: Context) {
        AppBaseInitializerEntryPoint.getBaseInitializer(context).orElse(null)?.init()
        Timber.i("Base initialization completed.")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

@EntryPoint
@InstallIn(SingletonComponent::class)
private interface AppBaseInitializerEntryPoint {
    fun getBaseInitializer(): Optional<AppBaseInitializer>

    companion object {
        fun getBaseInitializer(context: Context): Optional<AppBaseInitializer> {
            val appContext = context.applicationContext ?: throw IllegalStateException()
            return EntryPointAccessors.fromApplication(
                appContext,
                AppBaseInitializerEntryPoint::class.java
            ).getBaseInitializer()
        }
    }
}
