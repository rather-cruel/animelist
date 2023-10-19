package com.rathercruel.animelist.anime.models;

/**
 * @author Rather Cruel
 */
public class RelatedContent {
    private String contentID;
    private String type;
    private String relation;
    private String name;
    private String url;

    public RelatedContent() {}

    public RelatedContent(String contentID, String type, String relation, String name, String url) {
        this.contentID = contentID;
        this.type = type;
        this.relation = relation;
        this.name = name;
        this.url = url;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
