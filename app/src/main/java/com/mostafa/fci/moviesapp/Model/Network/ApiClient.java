package com.mostafa.fci.moviesapp.Model.Network;

import android.content.Context;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLs.MAIN_URL)
                    .client(new OkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}