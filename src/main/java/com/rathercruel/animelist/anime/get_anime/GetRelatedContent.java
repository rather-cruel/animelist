package com.rathercruel.animelist.anime.get_anime;

import com.rathercruel.animelist.anime.anime_page.RelatedContent;
import com.rathercruel.animelist.cache.Caching;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rather Cruel
 */
public class GetRelatedContent {
    public List<RelatedContent> getRelatedContent(URL urlObject, int intAnimeID, String contentType) throws IOException {
        List<RelatedContent> relatedContentList = new ArrayList<>();
        Caching caching = new Caching(urlObject, intAnimeID, contentType, "related");
        JSONObject data = caching.connection().getJSONObject("data");

        if (caching.getResponseCode() == HttpsURLConnection.HTTP_OK || caching.getResponseCode() == 0) {
            JSONArray relations = (JSONArray) data.get("relations");
            for (int i = 0; i < relations.toList().size(); i++) {
                JSONObject relationsObject = (JSONObject) relations.get(i);
                String relation = relationsObject.get("relation").toString();

                JSONArray entry = (JSONArray) relationsObject.get("entry");
                JSONObject entryContent = (JSONObject) entry.get(0);
                String id = entryContent.get("mal_id").toString();
                String type = entryContent.get("type").toString();
                String name = entryContent.get("name").toString();
                String url = entryContent.get("url").toString();
                relatedContentList.add(new RelatedContent(id, type, relation, name, url));
            }
        } else {
            System.out.println("Response CODE: " + caching.getResponseCode());
        }
        return relatedContentList;
    }
}
