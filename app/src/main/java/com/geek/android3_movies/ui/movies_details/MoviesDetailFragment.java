package com.geek.android3_movies.ui.movies_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geek.android3_movies.R;
import com.geek.android3_movies.base.BaseFragment;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.databinding.FragmentMoviesDetailBinding;

public class MoviesDetailFragment extends BaseFragment<FragmentMoviesDetailBinding> {

    private MoviesDetailViewModel viewModel;
    private Movies movies = new Movies();
    private String movieId;

    public MoviesDetailFragment() {}

    @Override
    protected FragmentMoviesDetailBinding bind() {
        return FragmentMoviesDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(MoviesDetailViewModel.class);

    }

    private void getArgs() {
        movies = (Movies) getArguments().getSerializable("movie");
        if (movies != null){
            Glide.with(binding.getRoot().getContext()).load(movies.getImage()).centerCrop().into(binding.ivImageDetail);
            binding.tvNameDetail.setText(movies.getTitle());
            binding.tvContentDetail.setText(movies.getDescription());
            movieId = movies.getId();
        }
    }

    @Override
    protected void setupObservers() {
        getArgs();
        viewModel.getMovieDetail(movieId).observe(getViewLifecycleOwner(), new Observer<Resource<Movies>>() {
            @Override
            public void onChanged(Resource<Movies> moviesResource) {
                switch (moviesResource.status) {
                    case LOADING:{
                        binding.progressDetailFrag.setVisibility(View.VISIBLE); break;
                    }
                    case SUCCESS:{
                        binding.tvNameDetail.setText(moviesResource.data.getTitle());
                        binding.tvContentDetail.setText(moviesResource.data.getDescription());
                        Glide.with(binding.getRoot().getContext()).load(moviesResource.data.getImage()).centerCrop().into(binding.ivImageDetail);
                        break;
                    }
                    case ERROR:{
                        binding.tvErrorDetail.setText(moviesResource.message);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}