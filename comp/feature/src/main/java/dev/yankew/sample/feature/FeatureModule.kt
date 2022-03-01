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

package dev.yankew.sample.feature

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.yankew.sample.domain.event.AppLaunchedEvent
import dev.yankew.sample.domain.event.EventHandler
import dev.yankew.sample.domain.repository.FeatureRepository
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FeatureDataStore

@Module
@InstallIn(SingletonComponent::class)
object FeatureModule {
    @Provides
    @IntoSet
    fun appLaunchedHandler(
        handlers: FeatureEventHandlers
    ): EventHandler<AppLaunchedEvent> = handlers.appLaunchedHandler

    @Provides
    @Singleton
    @FeatureDataStore
    fun preferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() }
        ) { context.preferencesDataStoreFile("feature") }
}

@Module
@InstallIn(SingletonComponent::class)
interface FeatureInterfaceModule {
    @Binds
    fun elapseDataSource(dataSource: ElapseDataSourceImpl): ElapseDataSource

    @Binds
    fun featureRepository(repository: FeatureRepositoryImpl): FeatureRepository
}
