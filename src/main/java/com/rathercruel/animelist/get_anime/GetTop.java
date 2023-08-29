package com.rathercruel.animelist.get_anime;

import com.rathercruel.animelist.anime_list.Anime;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rather Cruel
 */
public class GetTop {
    private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public void getAnimeTop(URL urlObject, List<Anime> animeList) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        String animeID;
        String animeTitle;
        String animeImageURL;
        String animeSynopsis = "";
        String animeMyAnimeListURL;
        String animeTitleEnglish = "";

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray data = (JSONArray) jsonObject.get("data");

            JSONObject pagination = (JSONObject) jsonObject.get("pagination");
            totalPages = Integer.parseInt(pagination.get("last_visible_page").toString());

            for (int i = 0; i < data.toList().size(); i++) {
                JSONObject dataIndex = (JSONObject) data.get(i);
                JSONObject images = (JSONObject) dataIndex.get("images");
                JSONObject jpg = (JSONObject) images.get("jpg");

                animeID = dataIndex.get("mal_id").toString();

                animeMyAnimeListURL = (String) dataIndex.get("url");
                animeTitle = (String) dataIndex.get("title");

                if (!jpg.isNull("large_image_url")) {
                    animeImageURL = (String) jpg.get("large_image_url");
                } else {
                    if (!jpg.isNull("image_url"))
                        animeImageURL = (String) jpg.get("image_url");
                    else if (!jpg.isNull("small_image_url"))
                        animeImageURL = (String) jpg.get("small_image_url");
                    else
                        animeImageURL = "https://placehold.co/424x600?text=No+Image";
                }

                if (!dataIndex.isNull("title_english"))
                    animeTitleEnglish = (String) dataIndex.get("title_english");

                if (!dataIndex.isNull("synopsis"))
                    animeSynopsis = (String) dataIndex.get("synopsis");

                animeList.add(new Anime(animeID, animeTitle, animeImageURL, animeSynopsis, animeMyAnimeListURL, animeTitleEnglish));
            }
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
    }
}
