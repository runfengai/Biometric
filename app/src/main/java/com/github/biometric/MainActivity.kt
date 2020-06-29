package com.github.biometric


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var biometricManager: BiometricManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = btn
        btn.setOnClickListener {
            biometricManager.authenticate()
        }
        supportFingerprint()
    }

   private fun supportFingerprint() {
        biometricManager = BiometricManager.Builder()
            .activity(this)
            .title(getString(R.string.fingerprint_hint))
            .negativeBtnText(getString(R.string.cancel))
            .fingerPrintCallback(fingerPrintCallback).build()
        biometricManager.checkState()
    }

    private var fingerPrintCallback: FingerPrintCallback? = object : FingerPrintCallback() {
        /**
         * =======================================================
         * device check
         * =======================================================
         */
        override fun onHardwareUnavailable() {
            gotPatternAuth()
        }


        override fun onNoneEnrolled() {
            gotPatternAuth()
        }

        @SuppressLint("SetTextI18n")
        override fun onAvailable() {
            tvState.setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    android.R.color.holo_green_dark
                )
            )
            tvState.text = "FingerPrint is OK"
        }

        override fun onNoHardware() {
            //
            gotPatternAuth()
        }

        /**
         * ============================================================
         * authenticate result
         * ============================================================
         */
        override fun onAuthenticateCancel() {
            Log.e(TAG, "finger->onAuthenticateCancel()")
        }

        override fun onAuthenticateFail() {
            Log.e(TAG, "finger->onAuthenticateFail()")
        }

        override fun onAuthenticateSuccess() {
            Log.e(TAG, "thread------>onAuthenticateSuccess   -->${Thread.currentThread().name}  ")
        }

    }

    //other way to unlock
    @SuppressLint("SetTextI18n")
    private fun gotPatternAuth() {
        tvState.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        tvState.text = "Finger print unavailable, maybe you can set one first"
    }

    companion object {
        const val TAG = "MainActivity"
    }
}



