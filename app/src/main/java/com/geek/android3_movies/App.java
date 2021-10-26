package com.geek.android3_movies;

import android.app.Application;

import com.geek.android3_movies.data.remote.ApiService;
import com.geek.android3_movies.data.remote.RetrofitClient;
import com.geek.android3_movies.data.repositories.MainRepositoryImpl;

public class App extends Application {

    public static ApiService service;
    public static MainRepositoryImpl repository;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient client = new RetrofitClient();
        service = client.provideApiService();
        repository = new MainRepositoryImpl();
    }
}
