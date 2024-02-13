package com.norm.myfcmpushnotifications

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.norm.myfcmpushnotifications.ui.theme.MyFcmPushNotificationsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission()
        setContent {
            MyFcmPushNotificationsTheme {
                requestNotificationPermission()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = viewModel.state
                    if (state.isEnteringToken) {
                        Log.d("MyLog", "Open EnterTokenDialog")
                        EnterTokenDialog(
                            token = state.remoteToken,
                            onTokenChange = viewModel::onRemoteTokenChange,
                            onSubmit = viewModel::onSubmitRemoteToken,
                        )
                    } else {
                        Log.d("MyLog", "Open ChatScreen\nToken: ${state.remoteToken}")
                        ChatScreen(
                            messageText = viewModel.state.messageText,
                            onMessageChange = viewModel::onMessageChange,
                            onMessageBroadcast = {
                                viewModel.sendMessage(isBroadcast = true)
                            },
                            onMessageSend = {
                                viewModel.sendMessage(isBroadcast = false)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermisson = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermisson) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }
}