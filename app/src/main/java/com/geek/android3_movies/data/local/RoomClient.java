package com.geek.android3_movies.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

public class RoomClient {

    public AppDatabase provideDataBase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "movies")
                .allowMainThreadQueries()
                .build();
    }

    public MoviesDao provideMoviesDao(AppDatabase database){
        return database.moviesDao();
    }
}
