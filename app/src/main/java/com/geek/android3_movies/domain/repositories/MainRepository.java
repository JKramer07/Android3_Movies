package com.geek.android3_movies.domain.repositories;

import androidx.lifecycle.MutableLiveData;

import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.models.Movies;

import java.util.List;

public interface MainRepository {

    MutableLiveData<Resource<List<Movies>>> fetchMovies();

    MutableLiveData<Resource<Movies>> fetchOneMovie(String id);
}
