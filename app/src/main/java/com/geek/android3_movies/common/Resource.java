package com.geek.android3_movies.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geek.android3_movies.data.models.Movies;

import java.util.List;

public class Resource<T> {

    @NonNull public final Status status;
    @NonNull public final T data;
    @NonNull public final String message;

    public Resource(@NonNull Status status, @NonNull T data, @NonNull String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data){
        return new Resource<>(Status.SUCCESS, data, null);
    }
    public static <T> Resource<T> error(String msg, @Nullable T data){
        return new Resource<>(Status.ERROR, data, msg);
    }
    public static <T> Resource<T> loading(@NonNull T data){
        return new Resource<>(Status.LOADING, data, null);
    }

    public enum Status{
        SUCCESS,
        ERROR,
        LOADING
    }
}
