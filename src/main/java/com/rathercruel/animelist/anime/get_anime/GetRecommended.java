package com.rathercruel.animelist.anime.get_anime;

import com.rathercruel.animelist.anime.anime_page.RecommendedAnime;
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
public class GetRecommended {
    public List<RecommendedAnime> getAnimeRecommendations(URL urlObject, int intAnimeID) throws IOException {
        List<RecommendedAnime> animeList = new ArrayList<>();
        Caching caching = new Caching(urlObject, intAnimeID, "anime", "recommendations");
        JSONArray data = caching.connection().getJSONArray("data");
        if (caching.getResponseCode() == HttpsURLConnection.HTTP_OK || caching.getResponseCode() == 0) {
            if (!data.toList().isEmpty()) {
                int size = 8;
                if (size > data.toList().size()) size = data.toList().size();
                for (int i = 0; i < size; i++) {
                    String image;

                    JSONObject object = (JSONObject) data.get(i);
                    JSONObject entry = (JSONObject) object.get("entry");
                    JSONObject images = (JSONObject) entry.get("images");
                    JSONObject jpg = (JSONObject) images.get("jpg");

                    if (!jpg.isNull("large_image_url")) {
                        image = (String) jpg.get("large_image_url");
                    } else {
                        if (!jpg.isNull("image_url"))
                            image = (String) jpg.get("image_url");
                        else if (!jpg.isNull("small_image_url"))
                            image = (String) jpg.get("small_image_url");
                        else
                            image = "https://placehold.co/424x600?text=No+Image";
                    }

                    String id = entry.get("mal_id").toString();
                    String title = (String) entry.get("title");
                    String url = (String) entry.get("url");
                    animeList.add(new RecommendedAnime(id, title, image, url));
                }
            }
        } else {
            System.out.println("Response CODE: " + caching.getResponseCode());
        }
        return animeList;
    }
}
