package com.rathercruel.animelist.anime_page;

/**
 * @author Rather Cruel
 */
public class RecommendedAnime {
    private String id;
    private String title;
    private String image;
    private String animeListURL;

    public RecommendedAnime() {}

    public RecommendedAnime(String id, String title, String image, String animeListURL) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.animeListURL = animeListURL;
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

    public String getAnimeListURL() {
        return animeListURL;
    }

    public void setAnimeListURL(String animeListURL) {
        this.animeListURL = animeListURL;
    }
}
