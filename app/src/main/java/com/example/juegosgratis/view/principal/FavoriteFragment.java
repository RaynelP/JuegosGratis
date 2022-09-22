package com.example.juegosgratis.view.principal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.juegosgratis.databinding.FragmentFavoriteBinding;
import com.example.juegosgratis.databinding.ItemFavoriteGameBinding;
import com.example.juegosgratis.view.adapters.genericAdapter.GenericAdapter;
import com.example.juegosgratis.view.adapters.genericAdapter.holders.HolderfavoriteGame;
import com.example.juegosgratis.viewModel.FavoriteGamesViewModel;
import com.example.juegosgratis.model.game.GameFavorite;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private FavoriteGamesViewModel favoriteGamesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding
                .inflate(inflater, container, false);

        configViewModel();
        configObservers();

        return binding.getRoot();
    }

    private void configViewModel(){
        favoriteGamesViewModel = new ViewModelProvider(this)
                .get(FavoriteGamesViewModel.class);
    }

    private void configObservers() {
        favoriteGamesViewModel.getFavoriteGames().observe(getViewLifecycleOwner(), gameFavorites -> {
            if(gameFavorites != null) configFavoriteList(gameFavorites);
        });

    }

    private void configFavoriteList(List<GameFavorite> favoriteGamesList){
        GenericAdapter<GameFavorite, ItemFavoriteGameBinding> adapter =
                new GenericAdapter.Builder<GameFavorite, ItemFavoriteGameBinding>()
                        .addList(favoriteGamesList)
                        .addViewData(new HolderfavoriteGame())
                        .addOnClickListener(new HolderfavoriteGame.onClick())
                        .build();
        binding.rvListaJuegosFavoritos.setAdapter(adapter);
    }
}