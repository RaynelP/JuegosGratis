package com.example.juegosgratis.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.model.game.GameFavorite;
import com.example.juegosgratis.repository.ExecuterThread;
import com.example.juegosgratis.repository.localDataBase.FuenteDeDatosFavoritosYVistos;
import com.example.juegosgratis.repository.network.Interfaces.IFavoriteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoriteGamesViewModel extends AndroidViewModel {

    private MutableLiveData<List<GameFavorite>> favoriteGames;
    private IFavoriteRepository favoriteRepository;

    public FavoriteGamesViewModel(@NonNull Application application) {
        super(application);
        getInstances(application);
        loadAllFavoriteGames();

    }

    private void getInstances(@NonNull Application application){
        favoriteGames = new MutableLiveData<>();
        favoriteRepository = FuenteDeDatosFavoritosYVistos
                .instanciar(application.getApplicationContext());
    }

    private void loadAllFavoriteGames() {
        try {
            List<GameFavorite> list = new ExecuterThread(favoriteRepository).obtenerJuegosFavoritos();
            favoriteGames.postValue(list);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteFavoriteGame(int idFavoriteGame){
        new ExecuterThread(favoriteRepository)
                .eliminarJuegoDeFavotitos(idFavoriteGame);
    }

    public LiveData<List<GameFavorite>> getFavoriteGames() {
        return favoriteGames;
    }
}
