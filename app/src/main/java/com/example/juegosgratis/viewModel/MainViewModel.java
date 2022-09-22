package com.example.juegosgratis.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.juegosgratis.repository.ExecuterThread;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.repository.network.implementations.GameRepositoryRetrofit;
import com.example.juegosgratis.model.GameConstans;
import com.example.juegosgratis.model.game.Game;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final IGameRepository gamesRepository;
    private final MutableLiveData<List<Game>> popularGamesList = new MutableLiveData<>();
    private final MutableLiveData<List<Game>> allGamesList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> navigateToPopularGamesList = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> error = new MutableLiveData<>(null);

    public static int CANTIDAD_JUEGOS_POPULARES = 10;

    public MainViewModel() {
        gamesRepository = new GameRepositoryRetrofit();
        getAllGames();
        getPopularGamesList();
    }

    private void getAllGames(){
        try {
            ArrayList<Game> list = (ArrayList<Game>) new ExecuterThread(gamesRepository)
                    .obtenerTodosLosJuegos();
            allGamesList.postValue(list);
        } catch (Exception e) {
            showError();
            e.printStackTrace();
        }
    }

    private void getPopularGamesList(){
        try {
            ArrayList<Game> list = (ArrayList<Game>) new ExecuterThread(gamesRepository)
                    .obtenerJuegosOrdenadosPor(GameConstans.MAS_POPULARES);
            popularGamesList.postValue(list);
        } catch (Exception e) {
            showError();
            e.printStackTrace();
        }
    }

    public List<String> getListaDeGenerosReducida(){
        ArrayList<String> lista = new ArrayList<>(9);
        lista.add(0, "shooter");
        lista.add(1, "mmorpg");
        lista.add(2, "strategy");
        lista.add(3, "racing");
        lista.add(4, "sports");
        lista.add(5, "survival");
        lista.add(6, "fantasy");
        lista.add(7, "action");
        lista.add(8, "All Category");

        return lista;
    }

    public LiveData<List<Game>> getAllGamesList() {
        return allGamesList;
    }

    public LiveData<Boolean> getNavigateToPopularGamesList() {
        return navigateToPopularGamesList;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public void doNavigateToGamesListPopulars(){
        navigateToPopularGamesList.postValue(true);
    }

    public void doneNavigateToGamesList(){
        navigateToPopularGamesList.postValue(null);
    }

    private void showError(){
        if(Boolean.FALSE.equals(error.getValue())){
            error.postValue(true);
        }
    }
    public void retry(){
        getAllGames();
        getPopularGamesList();
    }
}