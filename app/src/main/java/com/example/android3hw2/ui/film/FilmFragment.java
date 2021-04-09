package com.example.android3hw2.ui.film;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android3hw2.App;
import com.example.android3hw2.R;
import com.example.android3hw2.data.model.Film;
import com.example.android3hw2.data.remote.KeepFilms;

import java.util.ArrayList;
import java.util.List;

public class FilmFragment extends Fragment {

    private Adapter adapter;
    private RecyclerView recyclerView;
    private List<Film> listFilm = new ArrayList<>();
    private NavController navController;

    public FilmFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveFilms:
                NavController navController = Navigation.findNavController(getActivity(),
                        R.id.navHostFragment);
                navController.navigate(R.id.saveFilmFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);

        navController = Navigation.findNavController(getActivity(),
                R.id.navHostFragment);

        loadingFilm(view);
        return view;
    }

    private void loadingFilm(View view) {

        KeepFilms.getListFilmId(new KeepFilms.Result() {
            @Override
            public void onSuccess(List<Film> film) {
                listFilm.clear();
                listFilm.addAll(film);
                init(view);

                adapter.setSaveListener(film1 -> {
//                    String filmId = film1.getTitle();
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("film", filmId);
//                    Log.d("TAG", "onSuccess: " + filmId);

                    App.getAppDataBase().filmDao().insert(film1);

                    NavController navController = Navigation.findNavController(getActivity(),
                            R.id.navHostFragment);
                    navController.navigate(R.id.saveFilmFragment);
                });
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    private void init(View view) {

        recyclerView = view.findViewById(R.id.rvBasicFragment);
        adapter = new Adapter();
        adapter.setListener(film -> {
            String  filmId = film.getId();

            Bundle bundle = new Bundle();
            bundle.putString("film", filmId);

            NavController navController = Navigation.findNavController(getActivity(),
                    R.id.navHostFragment);
            navController.navigate(R.id.secondFragment, bundle);

        });
        adapter.addList(listFilm);
        recyclerView.setAdapter(adapter);

    }
}