package com.example.juegosgratis.repository.network.Interfaces;

import com.example.juegosgratis.model.game.GameDetail;
import com.example.juegosgratis.model.game.Game;

import java.util.List;

public interface IGameRepository {

    List<Game> getAllGames();

    GameDetail getGameDetail(Game game);

    GameDetail getGameDetail(int gameId);

    List<Game> getGameByPlatform(String platform);

    List<Game> getGameByCategory(String category);

    List<Game> getGamesOrderBy(String order);
}
