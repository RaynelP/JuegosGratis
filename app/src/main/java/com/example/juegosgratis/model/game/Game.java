package com.example.juegosgratis.model.game;

//clase que representa un Juego
public class Game implements Cloneable{

    private int id;
    private String title;
    private String thumbnail;
    private String short_description;
    private String game_url;
    private String genre;
    private String platform;
    private String publisher;
    private String developer;
    private String release_date;
    private String freetogame_profile_url;

    public Game() {
    }

    public Game(Game g) {
        new Game(g.getId(), g.getThumbnail(), g.getTitle(), g.getShort_description(), g.getGame_url(), g.getGenre(),
                g.getPlatform(), g.getPublisher(), g.getDeveloper(), g.getRelease_date(), g.getFreetogame_profile_url());
    }

    public Game(int id, String thumbnail, String title, String short_description, String game_url, String genre,
                String platform, String publisher, String developer, String release_date, String freetogame_profile_url) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.short_description = short_description;
        this.game_url = game_url;
        this.genre = genre;
        this.platform = platform;
        this.publisher = publisher;
        this.developer = developer;
        this.release_date = release_date;
        this.freetogame_profile_url = freetogame_profile_url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getGame_url() {
        return game_url;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getFreetogame_profile_url() {
        return freetogame_profile_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Game clone() throws CloneNotSupportedException {
        return (Game)super.clone();
    }
}
