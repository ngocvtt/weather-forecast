package com.ngocvtt.weatherforecast.model.weather

import org.json.JSONObject

class FeelsLike{

    var day: Double = 0.0
    var eve: Double = 0.0
    var morn: Double = 0.0
    var night: Double = 0.0

    constructor(jsonString: String){
        try {
            val obj = JSONObject(jsonString)
            this.fromJson(obj)
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    constructor(day: Double, eve: Double, morn: Double, night: Double) {
        this.day = day
        this.eve = eve
        this.morn = morn
        this.night = night
    }

    private fun fromJson(obj: JSONObject){
        day = obj.getDouble("day")
        eve = obj.getDouble("eve")
        morn = obj.getDouble("morn")
        night = obj.getDouble("night")
    }
}