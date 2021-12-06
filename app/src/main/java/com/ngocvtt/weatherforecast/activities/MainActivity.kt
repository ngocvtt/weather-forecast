package com.ngocvtt.weatherforecast.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.ngocvtt.weatherforecast.R
import com.ngocvtt.weatherforecast.model.weather.OpenWeather
import com.ngocvtt.weatherforecast.model.weather.WeatherInfo
import com.ngocvtt.weatherforecast.network.OpenWeatherService
import com.ngocvtt.weatherforecast.network.OpenWeatherServiceListener
import com.ngocvtt.weatherforecast.utils.Helper
import com.ngocvtt.weatherforecast.utils.RootedTrack
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val weatherList: ArrayList<WeatherInfo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Helper.init(applicationContext)
        RootedTrack.init()

        btn_getWeather.setOnClickListener {
            Helper.dismissKeyboard()
            val searchValue = edt_search.text.toString().trim()
            if (searchValue.length > 2){
                btn_getWeather.isEnabled = !btn_getWeather.isEnabled
                getWeather(searchValue)
            }
            else{
                Helper.showNoticeDialog(this@MainActivity, getString(R.string.msg_input_more_than_2_char))
            }
        }

    }


    private fun getWeather(city: String) {
        OpenWeatherService.getWeather(
            Helper.deAccent(city),
            listener = object : OpenWeatherServiceListener<OpenWeather> {
                override fun <T> onSuccess(data: T) {

                    weatherList.clear()
                    val result = data as OpenWeather
                    result.list.forEach {
                        val date = Date(it.dt * 1000)
                        val avgTemp = (it.temp.max + it.temp.min) / 2
                        val des = it.weather[0].description
                        weatherList.add(WeatherInfo(date, avgTemp, it.pressure, it.humidity, des))
                    }
                    val displayName = "${result.city.name}, ${result.city.country}"
                    edt_search.setText(displayName)
                    edt_search.setSelection(displayName.length)
                    weatherListView.adapter = ArrayAdapter(
                        this@MainActivity,
                        R.layout.weather_cell,
                        weatherList.map { it.displayValue })

                    btn_getWeather.isEnabled = !btn_getWeather.isEnabled
                }

                override fun onFail(message: String) {
                    Helper.showErrorDialog(this@MainActivity, message)
                    btn_getWeather.isEnabled = !btn_getWeather.isEnabled
                }

            })
    }
}