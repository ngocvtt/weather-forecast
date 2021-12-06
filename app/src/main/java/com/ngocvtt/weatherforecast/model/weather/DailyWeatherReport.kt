package com.ngocvtt.weatherforecast.model.weather

import org.json.JSONObject

class DailyWeatherReport{

    var clouds: Double = 0.0
    var deg: Int = 0
    var dt: Long = 0
    lateinit var feelsLike: FeelsLike
    var gust: Double = 0.0
    var humidity: Double = 0.0
    var pop: Double = 0.0
    var pressure: Int = 0
    var speed: Double = 0.0
    var sunrise: Int = 0
    var sunset: Int = 0
    lateinit var temp: Temperature
    lateinit var weather: ArrayList<WeatherDes>

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
        clouds: Double,
        deg: Int,
        dt: Long,
        feelsLike: FeelsLike,
        gust: Double,
        humidity: Double,
        pop: Double,
        pressure: Int,
        speed: Double,
        sunrise: Int,
        sunset: Int,
        temp: Temperature,
        weather: ArrayList<WeatherDes>
    ) {
        this.clouds = clouds
        this.deg = deg
        this.dt = dt
        this.feelsLike = feelsLike
        this.gust = gust
        this.humidity = humidity
        this.pop = pop
        this.pressure = pressure
        this.speed = speed
        this.sunrise = sunrise
        this.sunset = sunset
        this.temp = temp
        this.weather = weather
    }

    private fun fromJson(obj: JSONObject){
        clouds = obj.getDouble("clouds")
        deg = obj.getInt("deg")
        dt = obj.getLong("dt")
        feelsLike = FeelsLike(obj.getString("feels_like"))
        gust = obj.getDouble("gust")
        humidity = obj.getDouble("humidity")
        pop = obj.getDouble("pop")
        pressure = obj.getInt("pressure")
        speed = obj.getDouble("speed")
        sunrise = obj.getInt("sunrise")
        sunset = obj.getInt("sunset")
        temp = Temperature(obj.getString("temp"))
        weather = arrayListOf()
        val weather = obj.getJSONArray("weather")
        for (i in 0 until weather.length()){
            this.weather.add(WeatherDes(weather.getString(i)))
        }

    }
}