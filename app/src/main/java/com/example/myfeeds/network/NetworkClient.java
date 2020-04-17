package com.example.myfeeds.network;

import com.example.myfeeds.model.FeedModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Ashu Rajput on 9/13/2017.
 */

public class NetworkClient {

    private static Retrofit retrofit;
    private static NetworkClient networkClient = null;

    private NetworkClient() {
    }

    public static NetworkClient getNetworkClientInstance() {
        if (networkClient == null) {
            networkClient = new NetworkClient();
            initializeRetrofitLib();
        }
        return networkClient;
    }

    private static void initializeRetrofitLib() {

        String apiBaseURL = "https://dl.dropboxusercontent.com/s/";

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder requestBuilder = chain.request().newBuilder();
                        requestBuilder.header("Content-Type", "text/plain");
                        return chain.proceed(requestBuilder.build());
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(apiBaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public APIClient getApiClient() {
        return retrofit.create(APIClient.class);
    }

    public interface APIClient {

        @POST("2iodh4vg0eortkl/facts.json")
        Call<ResponseBody> callAPIExecutor(@Query("param") String jsonParam);
        @POST("2iodh4vg0eortkl/facts.json")
        Call<FeedModel> callAPI(@Query("param") String jsonParam);

        @GET
        Call<ResponseBody> sendRequest(@Url String fullURL);
    }
}
