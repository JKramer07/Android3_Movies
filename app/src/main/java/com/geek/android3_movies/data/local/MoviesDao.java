package com.geek.android3_movies.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geek.android3_movies.data.models.Movies;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(List<Movies> movies);

    @Query("SELECT * FROM movies")
    List<Movies> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    Movies getMovie(String id);
}
