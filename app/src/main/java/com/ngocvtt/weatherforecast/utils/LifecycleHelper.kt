package com.ngocvtt.weatherforecast.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.*

class LifecycleHelper : Application.ActivityLifecycleCallbacks {
    companion object {
        internal val instance by lazy { LifecycleHelper() }
        private val stack = Stack<Activity>()
        internal val currentActivity: Activity
            get() {
                if (stack.isNotEmpty()) {
                    var top = stack.peek()
                    while (top.isFinishing || top.isDestroyed) {
                        stack.pop()
                        top = stack.peek()
                    }
                    return stack.peek()
                } else {
                    throw Exception("currentActivity is null")
                }
            }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
        if (!stack.contains(activity)) stack.push(activity)
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
        if (activity == stack.peek() && activity.isFinishing) stack.pop()
    }

    override fun onActivityPreStopped(activity: Activity) {
        if (stack.isNotEmpty() && activity == stack.peek()) stack.pop()
    }

    override fun onActivityStopped(activity: Activity) {
        if (stack.isNotEmpty() && activity == stack.peek()) stack.pop()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}