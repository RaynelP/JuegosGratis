package com.example.juegosgratis.viewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.juegosgratis.repository.ExecuterThread;
import com.example.juegosgratis.repository.network.Interfaces.IFavoriteRepository;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.model.game.GameFavorite;
import com.example.juegosgratis.util.GenericViewModel;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.view.MainActivity;
import java.util.ArrayList;
import java.util.List;

public class GameListViewModel extends GenericViewModel {

    private final IGameRepository gameRepository;
    private final IFavoriteRepository favoriteRepository;
    private final MutableLiveData<List<Game>> gamesList;

    public GameListViewModel(
            @NonNull IGameRepository gameRepository,
            @NonNull IFavoriteRepository favoriteRepository,
            @Nullable String keyWord,
            @Nullable String word) {
        this.gameRepository = gameRepository;
        this.favoriteRepository = favoriteRepository;
        gamesList = new MutableLiveData<>();

        loadList(keyWord, word);
    }

    private void loadList(String keyWord, String word){
        switch (keyWord) {
            case UtilNavigate.BUSQUEDA_POR_GENERO:
                if (word.equals("All Category")) {
                    loadAllGames();
                } else {
                    loadGamesByGenre(word);
                }
                break;
            case UtilNavigate.BUSQUEDA_POR_NOMBRE:
                break;
            case UtilNavigate.BUSQUEDA_POR_ORDEN:
                loadGamesListByOrder(word);
                break;
            default:
                loadAllGames();
                break;
        }
    }


    public void loadAllGames(){
        onLoanding();
        try {
            List<Game> list = new ExecuterThread(gameRepository)
                    .obtenerTodosLosJuegos();
            gamesList.postValue(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doneLoanding();
    }

    public void loadGamesByGenre(String genre){
        onLoanding();
        try {
            List<Game> list = new ExecuterThread(gameRepository)
                    .obtenerJuegosPorCategoria(genre);
            gamesList.postValue(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doneLoanding();
    }

    public void loadGamesListByOrder(String order) {
        onLoanding();
        try {
            List<Game> list = new ExecuterThread(gameRepository)
                    .obtenerJuegosOrdenadosPor(order);
            gamesList.postValue(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doneLoanding();
    }

    public void loadGamesListByName(String name) {
        onLoanding();
        try {
            List<Game> list = new ExecuterThread(gameRepository)
                    .obtenerJuegosPorNombre(name);
            gamesList.postValue(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doneLoanding();
    }

    public ArrayList<GameFavorite> loadAllFavoriteGames(){
        return favoriteRepository.getAllGames();
    }

    public void addToFavorite(Game game){
        new ExecuterThread(favoriteRepository)
                .agregarJuegoAFavotitos(new GameFavorite(game.getThumbnail(), game.getTitle(), game.getId()));
    }

    public void deleteToFavorite(int gameId){
        new ExecuterThread(favoriteRepository)
                .eliminarJuegoDeFavotitos(gameId);
    }

    public MutableLiveData<List<Game>> getGamesList() {
        return gamesList;
    }

}


