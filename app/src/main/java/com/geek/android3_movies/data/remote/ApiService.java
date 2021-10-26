package com.geek.android3_movies.data.remote;

import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.models.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/films/")
    Call<List<Movies>> fetchMovies();

    @GET("films/{id}")
    Call<Movies> fetchOneMovie(
            @Path("id") String id);
}
