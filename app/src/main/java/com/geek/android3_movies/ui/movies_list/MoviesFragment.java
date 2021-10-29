package com.geek.android3_movies.ui.movies_list;

import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geek.android3_movies.R;
import com.geek.android3_movies.base.BaseFragment;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.local.MoviesDao;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.databinding.FragmentMoviesBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoviesFragment extends BaseFragment<FragmentMoviesBinding> implements OnClickListener{

    @Inject
    MoviesDao moviesDao;

    private MovieAdapter adapter;
    private MoviesViewModel viewModel;

    public MoviesFragment(){}



    @Override
    protected FragmentMoviesBinding bind() {
        return FragmentMoviesBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(MoviesViewModel.class);
        setupRecycler();
    }

    private void setupRecycler() {
        adapter = new MovieAdapter();
        adapter.setListener(this);
        adapter.setMovie(moviesDao.getAllMovies());
        binding.moviesRv.setAdapter(adapter);
    }

    @Override
    protected void setupObservers() {
        viewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<Resource<List<Movies>>>() {
            @Override
            public void onChanged(Resource<List<Movies>> moviesResource) {
                switch (moviesResource.status){
                    case SUCCESS:{
                        adapter.setMovie(moviesResource.data);
                        binding.rvProgressBar.setVisibility(View.GONE);
                        break;
                    }
                    case LOADING: {
                        binding.rvProgressBar.setVisibility(View.VISIBLE);
                        break;
                    }
                    case ERROR: {
                        binding.tvError.setText(moviesResource.message);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onClick(Movies movies) {
        openDetailFragment(movies);
    }

    private void openDetailFragment(Movies movies) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movies);
        navController.navigate(R.id.moviesDetailFragment, bundle);
    }

    @Override
    public void onLongClick(int id) {

    }
}