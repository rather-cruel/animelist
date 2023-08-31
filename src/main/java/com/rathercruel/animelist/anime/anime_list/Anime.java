package com.rathercruel.animelist.anime.anime_list;

/**
 * @author Rather Cruel
 */
public class Anime {
    private String id;
    private String title;
    private String image;
    private String synopsis;
    private String animeListURL;
    private String titleEnglish;

    public Anime() {
    }

    public Anime(String id,
                 String title,
                 String image,
                 String synopsis,
                 String animeListURL,
                 String titleEnglish) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.synopsis = synopsis;
        this.animeListURL = animeListURL;
        this.titleEnglish = titleEnglish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }
}
