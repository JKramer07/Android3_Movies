package com.geek.android3_movies.ui.movies_list;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geek.android3_movies.data.models.Movies;
import com.geek.android3_movies.databinding.ItemMoviesBinding;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movies> list = new ArrayList<>();
    private OnClickListener listener;

    public MovieAdapter(){}
    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setMovie(List<Movies> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMoviesBinding binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMoviesBinding binding;


        public ViewHolder(@NonNull ItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Movies movies) {
            binding.tvMovieName.setText(movies.getTitle());
            binding.tvMovieDes.setText(movies.getDescription());

            Glide.with(binding.getRoot().getContext()).load(movies.getImage()).centerCrop().into(binding.ivMovie);

            itemView.setOnClickListener(v->{
                listener.onClick(movies);
            });
        }
    }
}

interface OnClickListener{
    void onClick(Movies movies);
    void onLongClick(int id);
}
