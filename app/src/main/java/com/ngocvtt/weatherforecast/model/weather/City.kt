package com.ngocvtt.weatherforecast.model.weather

import org.json.JSONObject

class City{

    lateinit var coord: Coordinate
    lateinit var country: String
    var id: Int = 0
    lateinit var name: String
    var population: Int = 0
    var timezone: Int = 0


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
        coord: Coordinate,
        country: String,
        id: Int,
        name: String,
        population: Int,
        timezone: Int
    ) {
        this.coord = coord
        this.country = country
        this.id = id
        this.name = name
        this.population = population
        this.timezone = timezone
    }

    private fun fromJson(obj: JSONObject){
        coord = Coordinate(obj.getString("coord"))
        country = obj.getString("country")
        id = obj.getInt("id")
        name = obj.getString("name")
        population = obj.getInt("population")
        timezone = obj.getInt("timezone")
    }
}