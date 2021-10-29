package com.geek.android3_movies.ui.movies_list;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geek.android3_movies.App;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.data.repositories.MainRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MoviesViewModel extends ViewModel {

    private MainRepositoryImpl repository;

    @Inject
    public MoviesViewModel(MainRepositoryImpl mainRepository){
        this.repository = mainRepository;
    }

    MutableLiveData<Resource<List<Movies>>> getMovies(){
        return repository.fetchMovies();
    }

}


