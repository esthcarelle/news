package com.mine.news.ui.theme.components

import androidx.biometric.BiometricManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.mine.news.utils.BiometricAuthenticator

@Composable
fun BiometricAuthenticationScreen() {
    val context = LocalContext.current as FragmentActivity
    val biometricManager = BiometricManager.from(context)
    when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
        BiometricManager.BIOMETRIC_SUCCESS -> {
            BiometricAuthenticator.authenticateWithBiometric(context)
        }

        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {

        }

        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {

        }

        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {

        }

        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {

        }

        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {

        }

        BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {

        }
    }
}