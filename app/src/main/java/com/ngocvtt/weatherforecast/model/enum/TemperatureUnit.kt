package com.ngocvtt.weatherforecast.model.enum

enum class TemperatureUnit{
    Kelvin,
    Celsius,
    Fahrenheit;

    fun getValue(): String{
        return when(this){
            Kelvin -> "Default"
            Celsius -> "Metric"
            Fahrenheit -> "Imperial"
            else -> ""
        }
    }

    fun getName(): String{
        return when(this){
            Kelvin -> "Kelvin"
            Celsius -> "Celsius"
            Fahrenheit -> "Fahrenheit"
            else -> ""
        }
    }

}