package com.example.android3hw2.ui.film;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3hw2.R;
import com.example.android3hw2.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Film> list = new ArrayList<>();
    private Listener listener;
    private ListenerSave listenerSave;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<Film> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setSaveListener(ListenerSave listener) {
        this.listenerSave = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private Button btnSave;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView  = itemView.findViewById(R.id.txtViewFilm);
            btnSave = itemView.findViewById(R.id.btnSave);

            itemView.setOnClickListener(v -> listener.
                    onFilmClick(list.get(getAdapterPosition())));

            btnSave.setOnClickListener(v -> listenerSave.
                    onFilmSave(list.get(getAdapterPosition())));

        }

        public void bind(Film film) {
            textView.setText(film.getTitle());
        }
    }

    interface Listener{
        void onFilmClick(Film film);
    }

    interface ListenerSave{
        void onFilmSave(Film film);
    }
}
