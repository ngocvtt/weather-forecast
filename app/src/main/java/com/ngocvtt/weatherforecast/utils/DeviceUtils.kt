package com.ngocvtt.weatherforecast.utils

import android.os.Build
import java.io.File

object DeviceUtils {
    fun isRooted(): Boolean {

        // get from build info
        val buildTags = Build.TAGS
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true
        }

        // check if /system/app/Superuser.apk is present
        try {
            val file = File("/system/app/Superuser.apk")
            if (file.exists()) {
                return true
            }
        } catch (e1: Exception) {
            // ignore
        }

        // try executing commands
        if (!canExecuteCommand("su")) if (findBinary("su")) return true
            return false
    }

    private fun findBinary(binaryName: String): Boolean {
        var found = false
        if (!found) {
            val places = arrayOf(
                "/sbin/", "/system/bin/", "/system/xbin/",
                "/data/local/xbin/", "/data/local/bin/",
                "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"
            )
            for (where in places) {
                if (File(where + binaryName).exists()) {
                    found = true
                    break
                }
            }
        }
        return found
    }

    // executes a command on the system
    private fun canExecuteCommand(command: String): Boolean {
        return try {
            Runtime.getRuntime().exec(command)
            true
        } catch (e: Exception) {
            false
        }
    }
}