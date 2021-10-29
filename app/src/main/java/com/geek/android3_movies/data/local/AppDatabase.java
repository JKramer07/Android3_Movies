package com.geek.android3_movies.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geek.android3_movies.data.models.Movies;

@Database(entities = {Movies.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MoviesDao moviesDao();
}
