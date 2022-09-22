package com.example.juegosgratis.repository.network.adapters;

import com.example.juegosgratis.model.game.GameDetail;
import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.model.game.RequisitosMinimos;
import com.example.juegosgratis.model.game.ScreenShot;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;
import java.util.LinkedList;

public class DeserializerFullGame implements JsonDeserializer<GameDetail> {

    public DeserializerFullGame() {
    }

    public DeserializerFullGame(Game game) {
        //obtengo juego basico
        this.game = game;
    }

    @Override
    public GameDetail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if(game == null){
            int id = jsonObject.get("id").getAsInt();
            String title = jsonObject.get("title").getAsString();
            String thumbnail = jsonObject.get("thumbnail").getAsString();
            String short_description = jsonObject.get("short_description").getAsString();
            String game_url = jsonObject.get("game_url").getAsString();
            String genre = jsonObject.get("genre").getAsString();
            String platform = jsonObject.get("platform").getAsString();
            String publisher = jsonObject.get("publisher").getAsString();
            String developer = jsonObject.get("developer").getAsString();
            String release_date = jsonObject.get("release_date").getAsString();
            String freetogame_profile_url = jsonObject.get("freetogame_profile_url").getAsString();
            game = new Game(id, thumbnail, title, short_description, game_url, genre,
                     platform, publisher, developer, release_date, freetogame_profile_url);
        }

        //obtengo descripcion
        String descripcion = jsonObject.get("description").getAsString();

        //obtengo requisitos minimos
        JsonObject jsonRequisitosMinimos = jsonObject.getAsJsonObject("minimum_system_requirements");
        //String os = jsonRequisitosMinimos.get("os").getAsString();
        String os = jsonRequisitosMinimos.get("os") != JsonNull.INSTANCE ? jsonRequisitosMinimos.get("os").getAsString() : null;
        String processor = jsonRequisitosMinimos.get("processor") != JsonNull.INSTANCE ? jsonRequisitosMinimos.get("processor").getAsString() : null;
        //String processor = jsonRequisitosMinimos.get("processor").getAsString();
        String memory = jsonRequisitosMinimos.get("memory") != JsonNull.INSTANCE ? jsonRequisitosMinimos.get("memory").getAsString() : null;

        //String memory = jsonRequisitosMinimos.get("memory").getAsString();
        String graphics = jsonRequisitosMinimos.get("graphics") != JsonNull.INSTANCE ? jsonRequisitosMinimos.get("graphics").getAsString() : null;

        //String graphics = jsonRequisitosMinimos.get("graphics").getAsString();
        String storage = jsonRequisitosMinimos.get("storage") != JsonNull.INSTANCE ? jsonRequisitosMinimos.get("storage").getAsString() : null;
        //String storage = jsonRequisitosMinimos.get("storage").getAsString();
        RequisitosMinimos requisitosMinimos = new RequisitosMinimos(os, processor, memory, graphics, storage);

        //obtengo imagenes
        LinkedList<ScreenShot> listScreenShots = new LinkedList<>();
        JsonArray arrayImagenes = jsonObject.getAsJsonArray("screenshots");
        for (int i = 0; i < arrayImagenes.size(); i++) {
            Integer id = arrayImagenes.get(i).getAsJsonObject().get("id").getAsInt();
            String linkImagen = arrayImagenes.get(i).getAsJsonObject().get("image").getAsString();
            listScreenShots.add(new ScreenShot(id, linkImagen));
        }


        return new GameDetail(game, requisitosMinimos, listScreenShots, descripcion);
    }
    private Game game;
}
