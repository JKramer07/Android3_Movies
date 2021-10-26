package com.geek.android3_movies.ui.movies_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geek.android3_movies.App;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.data.repositories.MainRepositoryImpl;

import java.util.List;

public class MoviesViewModel extends ViewModel {

    MutableLiveData<Resource<List<Movies>>> getMovies(){
        return App.repository.fetchMovies();
    }
}
