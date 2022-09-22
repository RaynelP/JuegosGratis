package com.example.juegosgratis.model.game;

import java.util.List;

public class GameDetail {

    private Game game;
    private RequisitosMinimos requisitosMinimos;
    private String descripcion;
    private List<ScreenShot> images;

    public GameDetail(){}

    public GameDetail(Game game, RequisitosMinimos requisitosMinimos, List<ScreenShot> imagenes, String descripcion){
        this.game = game;
        this.requisitosMinimos = requisitosMinimos;
        this.images = imagenes;
        this.descripcion = descripcion;
    }

    public RequisitosMinimos getRequisitosMinimos() {
        return requisitosMinimos;
    }

    public String getDescription() {
        return descripcion;
    }

    public List<ScreenShot> getImages() {
        return images;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setRequisitosMinimos(RequisitosMinimos requisitosMinimos) {
        this.requisitosMinimos = requisitosMinimos;
    }

    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImages(List<ScreenShot> images) {
        this.images = images;
    }

}
