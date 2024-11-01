package com.example.weatherapp

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: Weather
)

data class Main (
    val temp: Float,
    val humidity: Int
)

data class Weather (
    val description: String,
    val humidity: Int
)