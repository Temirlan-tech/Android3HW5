package com.example.android3hw2.data.remote;

import com.example.android3hw2.data.model.Film;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeepFilms {

    public static void getListFilmId(Result result) {

        RetrofitBuilder
                .getInstance()
                .getAllFilms()
                .enqueue(new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            result.onSuccess(response.body());
                        } else {
                            result.onFailure("ERROR");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {
                        result.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public static void getListFilmId(String filmId, FilmClick filmClick) {
        RetrofitBuilder
                .getInstance()
                .getFilmId(filmId)
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            filmClick.onSuccess(response.body());
                        } else {
                            filmClick.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {

                    }
                });
    }

    public interface Result {
        void onSuccess(List<Film> film);

        void onFailure(String error);
    }

    public interface FilmClick {
        void onSuccess(Film film);

        void onFailure(String error);
    }
}
