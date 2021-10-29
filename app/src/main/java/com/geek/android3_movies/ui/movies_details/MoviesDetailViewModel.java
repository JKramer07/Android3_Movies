package com.geek.android3_movies.ui.movies_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geek.android3_movies.App;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.data.repositories.MainRepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MoviesDetailViewModel extends ViewModel {

    private MainRepositoryImpl repository;

    @Inject
    public MoviesDetailViewModel(MainRepositoryImpl mainRepository){
        this.repository = mainRepository;
    }

    MutableLiveData<Resource<Movies>> getMovieDetail(String id){
        return repository.fetchOneMovie(id);
    }
}
