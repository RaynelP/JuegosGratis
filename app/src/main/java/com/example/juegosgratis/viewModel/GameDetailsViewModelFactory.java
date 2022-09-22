package com.example.juegosgratis.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;

public class GameDetailsViewModelFactory implements ViewModelProvider.Factory {
    private IGameRepository gameRepository;
    private int gameId;

    public GameDetailsViewModelFactory(
            @NonNull IGameRepository gameRepository,
            @NonNull int gameId) {

        this.gameRepository = gameRepository;
        this.gameId = gameId;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GameDetailsViewModel(gameRepository, gameId);
    }

}