package com.ngocvtt.weatherforecast.model.weather

import org.json.JSONObject

class WeatherDes{
    var description: String = ""
    var icon: String = ""
    var id: Int = 0
    var main: String = ""

    constructor(jsonString: String){
        try {
            val obj = JSONObject(jsonString)
            this.fromJson(obj)
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    constructor(description: String, icon: String, id: Int, main: String) {
        this.description = description
        this.icon = icon
        this.id = id
        this.main = main
    }

    private fun fromJson(obj: JSONObject){
        description = obj.getString("description")
        icon = obj.getString("icon")
        id = obj.getInt("id")
        main = obj.getString("main")
    }
}