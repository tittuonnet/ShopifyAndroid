package com.merclain.shopify;

import android.app.Application;

import com.shopify.buy3.GraphClient;
import com.shopify.buy3.HttpCachePolicy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by tittuvarghese on 08/09/17.
 */
public class GraphClientShopify extends Application{
    public static GraphClient graphClient;

    public static GraphClient graphClient() {
        return graphClient;
    }

    @Override
    public void onCreate() {
        initializeGraphClient();
        super.onCreate();
    }

    private void initializeGraphClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY))
                .build();

        graphClient = GraphClient.builder(this)
                .shopDomain("https://love-my-fashions.myshopify.com")
                .accessToken("c7d8a5d231b7c41fc524f3675284d49f")
                .httpClient(httpClient)
                .httpCache(getCacheDir(), 1024 * 1024 * 10)
                .defaultHttpCachePolicy(HttpCachePolicy.CACHE_FIRST.expireAfter(20, TimeUnit.MINUTES))
                .build();
    }
}
