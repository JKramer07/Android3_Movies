package com.geek.android3_movies.ui.movies_details;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.geek.android3_movies.R;
import com.geek.android3_movies.base.BaseFragment;
import com.geek.android3_movies.common.Resource;
import com.geek.android3_movies.data.local.MoviesDao;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.databinding.FragmentMoviesDetailBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoviesDetailFragment extends BaseFragment<FragmentMoviesDetailBinding> {

    @Inject
    MoviesDao moviesDao;

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

        viewModel.getMovieDetail(movieId);
        getArgs();
        isOnline(getContext());

    }

    private void initRoom() {
        Movies moviesRoom = moviesDao.getMovie(movieId);

        Glide.with(this).load(moviesRoom.getImage()).centerCrop().into(binding.ivImageDetail);
        binding.tvNameDetail.setText(moviesRoom.getTitle());
        binding.tvContentDetail.setText(moviesRoom.getDescription());
    }

    private void getArgs() {
        movies = (Movies) getArguments().getSerializable("movie");
        if (movies != null) {
            Glide.with(binding.getRoot().getContext()).load(movies.getImage()).centerCrop().into(binding.ivImageDetail);
            binding.tvNameDetail.setText(movies.getTitle());
            binding.tvContentDetail.setText(movies.getDescription());
            movieId = movies.getId();
        }

    }

    @Override
    protected void setupObservers() {

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
                        if (isOnline(getContext())){
                            binding.tvErrorDetail.setText(null);
                            binding.progressDetailFrag.setVisibility(View.GONE);
                        } else {
                            binding.tvErrorDetail.setText(moviesResource.message);
                            binding.progressDetailFrag.setVisibility(View.GONE);
                        }
                        break;
                    }
                }
            }
        });
    }

    public boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            setupObservers();
            Toast.makeText(context, "Internet is connected!", Toast.LENGTH_SHORT).show();
        } else {
            initRoom();
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}