package com.ngocvtt.weatherforecast.utils

import android.util.Log
import com.ngocvtt.weatherforecast.BuildConfig

class Logger{
    companion object{
        private const val TAG = "[WEATHER FORECAST]"
        private val isDebug = BuildConfig.DEBUG

        fun printLog(vararg messages: Any) {
            if (isDebug) {
                Log.d(TAG, messages.joinToString(" "))
            }
        }

        fun errorLog(vararg errors: Any) {
            Log.e(TAG, errors.joinToString(" "))
        }

    }
}