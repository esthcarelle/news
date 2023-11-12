package com.mine.news.utils

import android.os.Build
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import timber.log.Timber

class BiometricAuthenticator {
    companion object{
        fun authenticateWithBiometric(context: FragmentActivity) {
            val executor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                context.mainExecutor
            } else {
                TODO("VERSION.SDK_INT < P")
            }
            val biometricPrompt = BiometricPrompt(
                context,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        //TODO handle authentication success, proceed to HomeScreen
                        Timber.tag("TAG").d("Authentication successful!!!")
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        Timber.tag("TAG").e("onAuthenticationError")
                        //TODO Handle authentication errors.
                    }

                    override fun onAuthenticationFailed() {
                        Timber.tag("TAG").e("onAuthenticationFailed")
                        //TODO Handle authentication failures.
                    }
                })

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setDescription("Place your finger the sensor or look at the front camera to authenticate.")
                .setNegativeButtonText("Cancel")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                .build()

            biometricPrompt.authenticate(promptInfo)
        }
    }
}
