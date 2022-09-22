package com.example.juegosgratis.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.juegosgratis.repository.network.Interfaces.IFavoriteRepository;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.viewModel.GameListViewModel;

public class GameListViewModelFactory implements ViewModelProvider.Factory {
    private IGameRepository gameRepository;
    private IFavoriteRepository favoriteRepository;
    private String keyWord;
    private String word;

    public GameListViewModelFactory(IGameRepository gameRepository,
                                    IFavoriteRepository favoriteRepository,
                                    String keyWord,
                                    String word) {
        this.gameRepository = gameRepository;
        this.favoriteRepository = favoriteRepository;
        this.keyWord = keyWord;
        this.word = word;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GameListViewModel(gameRepository, favoriteRepository, keyWord, word);
    }

}