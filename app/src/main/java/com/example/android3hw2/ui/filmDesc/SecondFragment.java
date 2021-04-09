package com.example.android3hw2.ui.filmDesc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android3hw2.R;
import com.example.android3hw2.data.model.Film;
import com.example.android3hw2.data.remote.KeepFilms;

public class SecondFragment extends Fragment {

    private TextView textTitle;
    private TextView textDesc;
    private TextView textDirector;
    private TextView textProducer;
    private TextView textReleaseDate;
    private String filmId;
    private Film films;

    public SecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filmId = getArguments().getString("film");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        init(view);
        getFilm();
        return view;
    }

    private void init(View view) {
        textTitle = view.findViewById(R.id.textTitle);
        textDesc = view.findViewById(R.id.textDesc);
        textDirector = view.findViewById(R.id.textDirector);
        textProducer = view.findViewById(R.id.textProducer);
        textReleaseDate = view.findViewById(R.id.textreleaseDate);
    }

    private void getFilm() {
        KeepFilms.getListFilmId(filmId, new KeepFilms.FilmClick() {
            @Override
            public void onSuccess(Film film) {
                textTitle.setText(film.getTitle());
                textDesc.setText(film.getDescription());
                textDirector.setText(film.getDirector());
                textProducer.setText(film.getProducer());
                textReleaseDate.setText(film.getReleaseDate());
                films = film;
            }

            @Override
            public void onFailure(String error) {
            }})
        ;}
}

