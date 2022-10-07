package com.example.juegosgratis.view.gameList;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.juegosgratis.databinding.FragmentGamelistBinding;
import com.example.juegosgratis.repository.localDataBase.FuenteDeDatosFavoritosYVistos;
import com.example.juegosgratis.repository.network.Interfaces.IFavoriteRepository;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.repository.network.implementations.GameRepositoryRetrofit;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.view.MainActivity;
import com.example.juegosgratis.viewModel.GameListViewModel;
import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.viewModel.GameListViewModelFactory;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GamesListFragment extends Fragment{

    private GameListViewModel viewModel;
    private FragmentGamelistBinding binding;
    private GameListAdapter gameListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGamelistBinding
                .inflate(inflater, container, false);

        configViewModel();
        configObservers();

        return binding.getRoot();
    }

    private void configViewModel() {
        IGameRepository gameRepository =
                new GameRepositoryRetrofit();

        IFavoriteRepository favoriteRepository = FuenteDeDatosFavoritosYVistos
                .instanciar(getContext());

        requireArguments().putString("h", "");
        String keyWord =  requireArguments()
                .getString(UtilNavigate.TIPO_DE_BUSQUEDA, null);

        String word =  requireArguments()
                .getString(UtilNavigate.WORD, null);

        GameListViewModelFactory factory =
                new GameListViewModelFactory(
                        gameRepository,
                        favoriteRepository,
                        keyWord,
                        word);

        viewModel = new ViewModelProvider(this, factory)
                .get(GameListViewModel.class);
    }

    private void configObservers() {
        viewModel.getGamesList().observe(getViewLifecycleOwner(),
                games -> loadList(games)
        );

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if(isLoading != null){
                MainActivity activity = (MainActivity) requireActivity();
                if(isLoading){
                    activity.doShowProgress();
                }else{
                    activity.doneShowProgress();
                }
            }
        });

    }

    private void loadList(List<Game> gameList){
        gameListAdapter = new GameListAdapter(
                FuenteDeDatosFavoritosYVistos
                        .instanciar(getContext())
                        .getAllGames()
        );
        gameListAdapter.setGamesList(gameList);
        binding.rvListaDeJuegos.setAdapter(gameListAdapter);
    }

    public void showMessage(String message){
        Snackbar.make(getView(), message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

}