package com.example.juegosgratis.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.juegosgratis.model.game.GameDetail;
import com.example.juegosgratis.repository.ExecuterThread;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.util.GenericViewModel;


public class GameDetailsViewModel extends GenericViewModel {

    private final IGameRepository gameRepository;
    private final MutableLiveData<GameDetail> gameDetail;
    private final MutableLiveData<Boolean> openOrCloseDescription;
    private final MutableLiveData<Boolean> openOrCloseRequeriments;

    public GameDetailsViewModel(IGameRepository gameRepository, int gameId){
        gameDetail = new MutableLiveData<>(null);
        this.gameRepository = gameRepository;
        openOrCloseDescription = new MutableLiveData<>();
        openOrCloseRequeriments = new MutableLiveData<>();
        loadGameDetail(gameId);
    }

    public void loadGameDetail(int gameId){
        try {
            GameDetail game = new ExecuterThread(gameRepository)
                    .obtenerJuegoConDetalles(gameId);
            gameDetail.postValue(game);
        } catch (Exception e) {

        }
    }

    public LiveData<GameDetail> getGameDetail() {
        return gameDetail;
    }

    public LiveData<Boolean> getOpenOrCloseDescription() {
        return openOrCloseDescription;
    }

    public LiveData<Boolean> getOpenOrCloseRequeriments() {
        return openOrCloseRequeriments;
    }

    public void doneOpenOrCloseDescription(){
        openOrCloseDescription.postValue(null);
    }

    public void doneOpenOrCloseRequeriments(){
        openOrCloseRequeriments.postValue(null);
    }

}
