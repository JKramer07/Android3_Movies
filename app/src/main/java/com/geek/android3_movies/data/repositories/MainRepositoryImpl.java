package com.geek.android3_movies.data.repositories;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.geek.android3_movies.App;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.local.MoviesDao;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.data.remote.ApiService;
import com.geek.android3_movies.domain.repositories.MainRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositoryImpl implements MainRepository {

    private ApiService apiService;
    private MoviesDao moviesDao;

    @Inject
    public MainRepositoryImpl(ApiService api, MoviesDao mDao) {
        this.apiService = api;
        this.moviesDao = mDao;
    }

    @Override
    public MutableLiveData<Resource<List<Movies>>> fetchMovies() {
        MutableLiveData<Resource<List<Movies>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));
        apiService.fetchMovies().enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    moviesDao.insertAllMovies(response.body());
                } else {
                    liveData.setValue(Resource.error(response.errorBody().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

    @Override
    public MutableLiveData<Resource<Movies>> fetchOneMovie(String id) {
        MutableLiveData<Resource<Movies>> movieLiveData = new MutableLiveData<>();
        movieLiveData.setValue(Resource.loading(null));
        apiService.fetchOneMovie(id).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful() && response.body() != null){
                    movieLiveData.setValue(Resource.success(response.body()));
                } else {
                    movieLiveData.setValue(Resource.error(response.errorBody().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                movieLiveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return movieLiveData;
    }



}
