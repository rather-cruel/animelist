package com.rathercruel.animelist.anime.get_anime;

import com.rathercruel.animelist.anime.anime_page.RelatedContent;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rather Cruel
 */
public class GetRelatedContent {
    public List<RelatedContent> getRelatedContent(URL urlObject) throws IOException {
        List<RelatedContent> relatedContentList = new ArrayList<>();
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        String id;
        String type;
        String relation;
        String name;
        String url;

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject data = (JSONObject) jsonObject.get("data");

            JSONArray relations = (JSONArray) data.get("relations");

            for (int i = 0; i < relations.toList().size(); i++) {
                JSONObject relationsObject = (JSONObject) relations.get(i);
                relation = relationsObject.get("relation").toString();

                JSONArray entry = (JSONArray) relationsObject.get("entry");
                JSONObject entryContent = (JSONObject) entry.get(0);
                id = entryContent.get("mal_id").toString();
                type = entryContent.get("type").toString();
                name = entryContent.get("name").toString();
                url = entryContent.get("url").toString();
                relatedContentList.add(new RelatedContent(id, type, relation, name, url));
            }
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
        return relatedContentList;
    }
}
