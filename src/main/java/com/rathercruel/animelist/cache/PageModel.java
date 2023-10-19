//package com.rathercruel.animelist.cache;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "anime")
//public class PageModel {
//
//    @Id
//    private Long id;
//
//    @Column(name = "main_json")
//    private String infoJSON;
//
//    @Column(name = "related_json")
//    private String relatedJSON;
//
//    @Column(name = "recommendations_json")
//    private String recommendationsJSON;
//
//    public PageModel() {
//    }
//
//    public PageModel(Long id, String infoJSON, String relatedJSON, String recommendationsJSON) {
//        this.id = id;
//        this.infoJSON = infoJSON;
//        this.relatedJSON = relatedJSON;
//        this.recommendationsJSON = recommendationsJSON;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getInfoJSON() {
//        return infoJSON;
//    }
//
//    public void setInfoJSON(String infoJSON) {
//        this.infoJSON = infoJSON;
//    }
//
//    public String getRelatedJSON() {
//        return relatedJSON;
//    }
//
//    public void setRelatedJSON(String relatedJSON) {
//        this.relatedJSON = relatedJSON;
//    }
//
//    public String getRecommendationsJSON() {
//        return recommendationsJSON;
//    }
//
//    public void setRecommendationsJSON(String recommendationsJSON) {
//        this.recommendationsJSON = recommendationsJSON;
//    }
//}
