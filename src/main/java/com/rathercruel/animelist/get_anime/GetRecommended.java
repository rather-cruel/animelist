package com.rathercruel.animelist.get_anime;

import com.rathercruel.animelist.anime_list.Anime;
import com.rathercruel.animelist.anime_page.RecommendedAnime;
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
public class GetRecommended {
    public void getAnimeRecommendations(URL urlObject, List<RecommendedAnime> animeList) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        String animeID = "";

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

//            JSONObject jsonObject = new JSONObject(sb.toString());
//            JSONArray dataArray = (JSONArray) jsonObject.get("data");
//            for (int i = 0; i < 1; i++) {
//                JSONObject object = (JSONObject) dataArray.get(i);
//                JSONObject entry = (JSONObject) object.get("entry");
//                animeID = entry.get("mal_id").toString();
//
//                URL animeURL = new URL("https://api.jikan.moe/v4/anime/" + animeID);
//                GetAnime getAnime = new GetAnime();
//                List<String> arr = getAnime.getAnime(animeURL);
//                String animeTitle = arr.get(1);
//                String animeImageURL = arr.get(2);
//                String animeSynopsis = arr.get(3);
//                String animeMyAnimeListURL = arr.get(4);
//                String animeTitleEnglish = arr.get(5);
//                animeList.add(new Anime(animeID, animeTitle, animeImageURL, animeSynopsis, animeMyAnimeListURL, animeTitleEnglish));
//            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray data = (JSONArray) jsonObject.get("data");
            for (int i = 0; i < 8; i++) {
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
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
    }
}
