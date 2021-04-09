package com.example.android3hw2.ui.saveFilms;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw2.App;
import com.example.android3hw2.R;
import com.example.android3hw2.data.model.Film;

import java.util.ArrayList;

public class SaveFilmFragment extends Fragment {

    private NavController navController;
    private ArrayList<Film> films;
    private SaveFilmAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        films = (ArrayList<Film>) App.getAppDataBase().filmDao().getAll();
        adapter = new SaveFilmAdapter(films);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_save_film, container, false);
        navController = Navigation.findNavController(getActivity(),
                R.id.navHostFragment);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvSavedFilm);
        recyclerView.setAdapter(adapter);
    }
}