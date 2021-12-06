package com.ngocvtt.weatherforecast.network

import com.ngocvtt.weatherforecast.core.Config
import com.ngocvtt.weatherforecast.model.enum.TemperatureUnit
import com.ngocvtt.weatherforecast.model.weather.City
import com.ngocvtt.weatherforecast.model.weather.OpenWeather
import com.ngocvtt.weatherforecast.utils.Logger
import org.json.JSONObject

class OpenWeatherService {

    companion object{
        private val client = Client(Config.domain)

        private val headers = mapOf(
            "Content-Type" to "application/json",
            "Accept" to "application/json"
        )

        fun getWeather(city: String, cnt: Int = 7, unit: TemperatureUnit = TemperatureUnit.Celsius, listener: OpenWeatherServiceListener<OpenWeather>){
            val endPoint = "/data/2.5/forecast/daily"
            val params = mapOf(
                "q" to city,
                "cnt" to "$cnt",
                "appid" to Config.appId,
                "units" to unit.getValue()
            )
            client.get(endPoint, headers, params, object :Client.ClientListener{
                override fun success(jsonObject: JSONObject) {
                    val ow = OpenWeather(jsonObject.toString())
                    listener.onSuccess(ow)
                }

                override fun fail(error: String) {
                    Logger.printLog(error)
                    listener.onFail(error)
                }

            })
        }
    }
}

interface OpenWeatherServiceListener<T>{
    fun <T> onSuccess(data: T)
    fun onFail(message: String)
}