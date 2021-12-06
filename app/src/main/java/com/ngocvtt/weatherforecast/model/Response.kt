package com.ngocvtt.weatherforecast.model

sealed class Response<T>(val code: Int, val data: T?, message: String? = null){
    class Success<T>(code: Int, data: T): Response<T>(code, data)
    class Fail<T>(code: Int, message: String): Response<T>(code, null, message)
}