package com.ngocvtt.weatherforecast.model.weather

import org.json.JSONObject

class Coordinate{


    var lat: Double = 0.0
    var lon: Double = 0.0

    constructor(jsonString: String){
        try {
            val obj = JSONObject(jsonString)


            this.fromJson(obj)
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    constructor(lat: Double, lon: Double) {
        this.lat = lat
        this.lon = lon
    }

    private fun fromJson(obj: JSONObject){
        lat = obj.getDouble("lat")
        lon = obj.getDouble("lon")
    }
}