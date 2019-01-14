package com.example.kimutai.bakingapp.Networking.generator;

import com.example.kimutai.bakingapp.BuildConfig;
import com.example.kimutai.bakingapp.Networking.route;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dataservicegenerator {
    public static <S> S createservice(Class<S> serviceClass){
        String token="";
        Gson gson=new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();

        Retrofit.Builder builder=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(route.BASE_URL);

        OkHttpClient.Builder httpclient=new OkHttpClient.Builder()
                .readTimeout(90,TimeUnit.SECONDS)
                .connectTimeout(90,TimeUnit.SECONDS)
                .writeTimeout(90,TimeUnit.SECONDS)
                .cache(null);
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
            httpclient.addInterceptor(loggingInterceptor);
        }
        builder.client(httpclient.build());
        Retrofit retrofit=builder.build();
        return retrofit.create(serviceClass);
    }
}
