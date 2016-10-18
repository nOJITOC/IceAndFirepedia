package com.softdesign.skillbranch.iceandfirepedia.data.network;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.softdesign.skillbranch.iceandfirepedia.data.network.interceptors.HeaderInterceptor;
import com.softdesign.skillbranch.iceandfirepedia.utils.AppConfig;
import com.softdesign.skillbranch.iceandfirepedia.utils.IceAndFireApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create());
    public static <S> S createService(Class<S> serviceClass){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(new HeaderInterceptor());
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(AppConfig.MAX_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(AppConfig.MAX_READ_TIMEOUT, TimeUnit.MILLISECONDS);

        httpClient.addNetworkInterceptor(new StethoInterceptor());


        Retrofit retrofit = sBuilder
                .client(httpClient.build())
                .baseUrl(AppConfig.BASE_URL)
                .build();
        return retrofit.create(serviceClass);
    }
}
