// WeatherResult.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements

parcelable WeatherResult {
    boolean isSuccess;  // Indicates if the request was successfully sent
    long datetime;      // DateTime in milliseconds when the request was sent
}