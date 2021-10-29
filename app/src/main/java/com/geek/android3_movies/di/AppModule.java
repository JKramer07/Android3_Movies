package com.geek.android3_movies.di;

import android.content.Context;

import com.geek.android3_movies.data.local.MoviesDao;
import com.geek.android3_movies.data.local.RoomClient;
import com.geek.android3_movies.data.remote.ApiService;
import com.geek.android3_movies.data.remote.RetrofitClient;
import com.geek.android3_movies.data.repositories.MainRepositoryImpl;
import com.geek.android3_movies.domain.repositories.MainRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static MainRepository provideRep(ApiService api, MoviesDao moviesDao){
        return new MainRepositoryImpl(api, moviesDao);
    }

    @Provides
    public static RetrofitClient provideRetrofit(){
        return new RetrofitClient();
    }

    @Provides
    public static ApiService provideApi(RetrofitClient retrofitClient){
        return retrofitClient.provideApiService();
    }

    @Provides
    public static RoomClient provideRoomClient(){
        return new RoomClient();
    }

    @Provides
    public static MoviesDao provideMoviesDao(RoomClient client, @ApplicationContext Context context){
        return client.provideMoviesDao(client.provideDataBase(context));
    }

}
