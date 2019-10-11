package com.example.islamiapp.apis;

import com.example.islamiapp.apis.model.RadioChannelsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("radios/radio_arabic.json")
    Call<RadioChannelsResponse> getRadioChannels();
}
