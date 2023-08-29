package com.rathercruel.animelist.get_anime;

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
public class GetAnime {
    public List<String> getAnime(URL urlObject) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        String animeID = "";
        String animeTitle = "";
        String animeImageURL = "";
        String animeSynopsis = "";
        String animeMyAnimeListURL = "";
        String animeTitleEnglish = "";

        String animeScore = "";
        String animeScoredBy = "";
        String animeRank = "";
        String animeType = "";
        String animeDuration = "";
        String animeRating = "";
        String animeBackground = "";
        String airing = "";
        String animeStudio = "";
        String animeYear = "";
        String animeFavorites = "";
        String animePopularity = "";
        String animeTrailer = "";

        List<String> animeProducersList = new ArrayList<>();

        List<String> animeGenresList = new ArrayList<>();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject data = (JSONObject) jsonObject.get("data");

            JSONObject images = (JSONObject) data.get("images");
            JSONObject jpg = (JSONObject) images.get("jpg");

            JSONObject trailer = (JSONObject) data.get("trailer");
            animeTrailer = trailer.get("embed_url").toString();

            animeScore = data.get("score").toString();
            animeScoredBy = data.get("scored_by").toString();
            animeRank = data.get("rank").toString();
            animeType = data.get("type").toString();
            animeDuration = data.get("duration").toString();
            animeRating = data.get("rating").toString();
            animeBackground = data.get("background").toString();

            JSONObject airiedObj = (JSONObject) data.get("aired");
            airing = airiedObj.get("string").toString();

            JSONArray studioArray = (JSONArray) data.get("studios");
            JSONObject studioData = (JSONObject) studioArray.get(0);
            animeStudio = studioData.get("name").toString();

            animeYear = data.get("year").toString();
            animeFavorites = data.get("favorites").toString();
            animePopularity = data.get("popularity").toString();

            JSONArray producers = (JSONArray) data.get("producers");
            for (int i = 0; i < producers.toList().size(); i++) {
                JSONObject producer = (JSONObject) producers.get(i);
                animeProducersList.add(producer.get("name").toString());
            }

            JSONArray genres = (JSONArray) data.get("genres");
            for (int i = 0; i < genres.toList().size(); i++) {
                JSONObject genre = (JSONObject) genres.get(i);
                animeGenresList.add(genre.get("name").toString());
            }

            animeID = data.get("mal_id").toString();

            animeMyAnimeListURL = (String) data.get("url");
            animeTitle = (String) data.get("title");

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

            if (!data.isNull("title_english"))
                animeTitleEnglish = (String) data.get("title_english");

            if (!data.isNull("synopsis"))
                animeSynopsis = (String) data.get("synopsis");
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
        String animeProducers = "";
        String animeGenres = "";
        for (int i = 0; i < animeProducersList.size(); i++) {
            if (i != animeProducersList.size() - 1)
                animeProducers += animeProducersList.get(i) + ", ";
            else
                animeProducers += animeProducersList.get(i);
        }
        for (int i = 0; i < animeGenresList.size(); i++) {
            if (i != animeProducersList.size() - 1)
                animeGenres += animeGenresList.get(i) + ", ";
            else
                animeGenres += animeGenresList.get(i);
        }
        return List.of(
                animeID, animeTitle, animeImageURL, animeSynopsis, animeMyAnimeListURL, animeTitleEnglish,
                animeScore, animeScoredBy, animeRank, animeType, animeDuration, animeRating, animeBackground, airing,
                animeStudio, animeYear, animeFavorites, animePopularity, animeProducers, animeGenres, animeTrailer
        );
    }
}
