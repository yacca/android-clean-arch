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

package dev.yankew.sample.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Register broadcast receiver to convert data in broadcast to Flow.
 * The receiver will be unregistered if the returned Flow is cancelled.
 *
 * @param filter Selects the Intent broadcasts to be received.
 * @param onStartListening A function that will run right after the registration of the receiver.
 *      It is useful when you do two-way communication via broadcast. The requesting data broadcast
 *      can be sent in this function.
 * @param extractData The function to extract data from the broadcast
 */
fun <T> Context.observeBroadcast(
    filter: IntentFilter,
    onStartListening: () -> Unit = {},
    extractData: (Context, Intent) -> T
): Flow<T> {
    return flow {
        val channel = Channel<T>(Channel.UNLIMITED)
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                channel.trySend(extractData(context, intent))
            }
        }
        registerReceiver(receiver, filter)
        onStartListening()
        try {
            channel.consumeEach {
                emit(it)
            }
        } finally {
            unregisterReceiver(receiver)
        }
    }
}
