// IWeatherCallback.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements

interface IWeatherCallback {
    void onSuccess(String response);
    void onException(String exception);
}