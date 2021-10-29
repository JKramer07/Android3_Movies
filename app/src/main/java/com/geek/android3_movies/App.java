package com.geek.android3_movies;

import android.app.Application;

import com.geek.android3_movies.data.remote.ApiService;
import com.geek.android3_movies.data.remote.RetrofitClient;
import com.geek.android3_movies.data.repositories.MainRepositoryImpl;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
