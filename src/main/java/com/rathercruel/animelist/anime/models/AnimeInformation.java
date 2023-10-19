package com.rathercruel.animelist.anime.models;

/**
 * @author Rather Cruel
 */
public class AnimeInformation {
    private String id;
    private String myAnimeListURL;
    private String image;
    private String title;
    private String titleEnglish;
    private String desc;
    private String score;
    private String scoredBy;
    private String rank;
    private String background;
    private String favorites;
    private String type;
    private String genres;
    private String popularity;
    private String published;

    private String rating;
    private String duration;
    private String studio;
    private String producers;
    private String year;
    private String trailerURL;
    private String episodes;
    private String licensors;

    public AnimeInformation() {}

    public AnimeInformation(
            String id, String title, String image, String desc, String myAnimeListURL,
            String titleEnglish, String score, String scoredBy, String rank, String type,
            String duration, String rating, String background, String published, String studio,
            String year, String favorites, String popularity, String producers, String genres,
            String trailerURL, String episodes, String licensors
    ) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.myAnimeListURL = myAnimeListURL;
        this.titleEnglish = titleEnglish;
        this.score = score;
        this.scoredBy = scoredBy;
        this.rank = rank;
        this.type = type;
        this.duration = duration;
        this.rating = rating;
        this.background = background;
        this.published = published;
        this.studio = studio;
        this.year = year;
        this.favorites = favorites;
        this.popularity = popularity;
        this.producers = producers;
        this.genres = genres;
        this.trailerURL = trailerURL;
        this.episodes = episodes;
        this.licensors = licensors;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMyAnimeListURL() {
        return myAnimeListURL;
    }

    public void setMyAnimeListURL(String myAnimeListURL) {
        this.myAnimeListURL = myAnimeListURL;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoredBy() {
        return scoredBy;
    }

    public void setScoredBy(String scoredBy) {
        this.scoredBy = scoredBy;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getLicensors() {
        return licensors;
    }

    public void setLicensors(String licensors) {
        this.licensors = licensors;
    }
}
