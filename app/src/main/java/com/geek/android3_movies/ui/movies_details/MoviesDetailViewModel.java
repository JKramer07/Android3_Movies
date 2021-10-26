package com.geek.android3_movies.ui.movies_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geek.android3_movies.App;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.models.Movies;

public class MoviesDetailViewModel extends ViewModel {

    MutableLiveData<Resource<Movies>> getMovieDetail(String id){
        return App.repository.fetchOneMovie(id);
    }
}
