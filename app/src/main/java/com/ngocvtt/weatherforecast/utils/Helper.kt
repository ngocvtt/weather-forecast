package com.ngocvtt.weatherforecast.utils

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.ngocvtt.weatherforecast.R

class Helper {
    companion object{

        fun init(context: Context){
            if (context is Application) {
                context.registerActivityLifecycleCallbacks(LifecycleHelper.instance)
            } else if (context.applicationContext is Application) {
                (context.applicationContext as Application).registerActivityLifecycleCallbacks(LifecycleHelper.instance)
            }
        }

        private fun showMessageDialog(context: Context, title: String, message: String, cancelable: Boolean = true){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setCancelable(cancelable)
            builder.show()
        }

        fun showNoticeDialog(context: Context, message: String,cancelable: Boolean = true){
            showMessageDialog(context, getString(R.string.notice), message, cancelable)
        }

        fun showErrorDialog(context: Context, message: String,cancelable: Boolean = true){
            showMessageDialog(context, getString(R.string.error), message, cancelable)
        }



        fun dismissKeyboard(){
            try{
                val imm: InputMethodManager = LifecycleHelper.currentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (LifecycleHelper.currentActivity.currentFocus != null)
                    imm.hideSoftInputFromWindow(LifecycleHelper.currentActivity.currentFocus?.applicationWindowToken, 0)
            }
            catch (e: Exception){
                Logger.printLog(e)
            }
        }

        fun deAccent(str: String): String {
            return Normalize.removeAccent(str);
        }

        fun getString(id: Int): String{
            return try {
                LifecycleHelper.currentActivity.getString(id)
            }catch (e: Exception){
                ""
            }
        }
    }
}