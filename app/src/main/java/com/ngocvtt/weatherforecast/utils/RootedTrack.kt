package com.ngocvtt.weatherforecast.utils

import androidx.lifecycle.*
import com.ngocvtt.weatherforecast.R

object RootedTrack: DefaultLifecycleObserver {

    fun init(){
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Logger.printLog("Rooted device checking...")
        if (DeviceUtils.isRooted()){
            Logger.printLog("Device is rooted!")
            Helper.showNoticeDialog(LifecycleHelper.currentActivity,  Helper.getString(R.string.msg_device_rooted), cancelable = false)
        }
        else{
            Logger.printLog("Device is not rooted!")
        }
    }

}