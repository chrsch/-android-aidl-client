// IWeather.aidl
package com.example.aidlserver;

// Provide weather info from https://api.open-meteo.com/v1/forecast?latitude=48.13743&longitude=11.57549&hourly=temperature_2m&timezone=Europe%2FBerlin&forecast_days=1

import com.example.aidlserver.IWeatherCallback;
import com.example.aidlserver.WeatherResult;

interface IWeather {
    WeatherResult getWeather(IWeatherCallback callback);
}