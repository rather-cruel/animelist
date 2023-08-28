package com.rathercruel.animelist.anime;

import jakarta.persistence.*;

/**
 * @author Rather Cruel
 */
public class Anime {
    private String name;
    private String image;
    private String synopsis;
    private String animeListURL;
    private String japaneseName;

    public Anime() {
    }

    public Anime(String name,
                 String image,
                 String synopsis,
                 String animeListURL,
                 String japaneseName) {
        this.name = name;
        this.image = image;
        this.synopsis = synopsis;
        this.animeListURL = animeListURL;
        this.japaneseName = japaneseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAnimeListURL() {
        return animeListURL;
    }

    public void setAnimeListURL(String animeListURL) {
        this.animeListURL = animeListURL;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }
}
