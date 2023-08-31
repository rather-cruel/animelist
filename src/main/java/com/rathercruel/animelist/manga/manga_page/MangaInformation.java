package com.rathercruel.animelist.manga.manga_page;

/**
 * @author Rather Cruel
 */
public class MangaInformation {
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

    private String status;
    private String members;
    private String authors;
    private String chapters;
    private String volumes;

    public MangaInformation() {}

    public MangaInformation(
            String id, String myAnimeListURL, String image, String title,
            String titleEnglish, String type, String chapters, String volumes,
            String status, String published, String score, String scoredBy,
            String rank, String popularity, String members, String favorites,
            String desc, String background, String authors, String genres
    ) {
        this.id = id;
        this.myAnimeListURL = myAnimeListURL;
        this.image = image;
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.type = type;
        this.chapters = chapters;
        this.volumes = volumes;
        this.status = status;
        this.published = published;
        this.score = score;
        this.scoredBy = scoredBy;
        this.rank = rank;
        this.popularity = popularity;
        this.members = members;
        this.favorites = favorites;
        this.desc = desc;
        this.background = background;
        this.authors = authors;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyAnimeListURL() {
        return myAnimeListURL;
    }

    public void setMyAnimeListURL(String myAnimeListURL) {
        this.myAnimeListURL = myAnimeListURL;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
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

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
