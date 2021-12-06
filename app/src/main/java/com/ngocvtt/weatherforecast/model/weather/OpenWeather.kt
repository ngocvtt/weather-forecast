package com.ngocvtt.weatherforecast.model.weather

import com.ngocvtt.weatherforecast.utils.Logger
import org.json.JSONObject

class OpenWeather{

    lateinit var city: City
    var cnt: Int = 0
    var cod: String = ""
    lateinit var  list: ArrayList<DailyWeatherReport>
    var message: Double = 0.0

    constructor(jsonString: String){
        try {
            val obj = JSONObject(jsonString)

            this.fromJson(obj)
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    constructor(
        city: City,
        cnt: Int,
        cod: String,
        list: ArrayList<DailyWeatherReport>,
        message: Double
    ) {
        this.city = city
        this.cnt = cnt
        this.cod = cod
        this.list = list
        this.message = message
    }

    private fun fromJson(obj: JSONObject){
        city = City(obj.getString("city"))
        cnt = obj.getInt("cnt")
        cod = obj.getString("cod")
        list = arrayListOf()
        val daily = obj.getJSONArray("list")
        Logger.printLog(daily.getString(0))
        for (i in 0 until daily.length()){
            list.add(DailyWeatherReport(daily.getString(i)))
            Logger.printLog(list)
        }

    }
}