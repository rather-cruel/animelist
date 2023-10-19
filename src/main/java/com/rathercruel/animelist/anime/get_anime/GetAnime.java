package com.rathercruel.animelist.anime.get_anime;

import com.rathercruel.animelist.anime.models.AnimeInformation;
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
public class GetAnime {
    public List<AnimeInformation> getAnime(URL urlObject, int intAnimeID) throws IOException {
        Caching caching = new Caching(urlObject, intAnimeID, "anime", "basic");
        JSONObject data = caching.connection().getJSONObject("data");

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
        String animeEpisodes = "";

        String animeProducers;
        String animeGenres;
        String animeLicensors;

        List<String> animeProducersList = new ArrayList<>();
        List<String> animeGenresList = new ArrayList<>();
        List<String> animeLicensorList = new ArrayList<>();

        if (caching.getResponseCode() == HttpsURLConnection.HTTP_OK || caching.getResponseCode() == 0) {
            try {
                JSONArray licensorsArray = (JSONArray) data.get("licensors");
                for (int i = 0; i < licensorsArray.toList().size(); i++) {
                    JSONObject licensors = (JSONObject) licensorsArray.get(i);
                    animeLicensorList.add(licensors.get("name").toString());
                }
            } catch (Exception ignored) {}

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
            animeEpisodes = data.get("episodes").toString();

            JSONObject airiedObj = (JSONObject) data.get("aired");
            airing = airiedObj.get("string").toString();

            try {
                JSONArray studioArray = (JSONArray) data.get("studios");
                JSONObject studioData = (JSONObject) studioArray.get(0);
                animeStudio = studioData.get("name").toString();
            } catch (Exception ignored) {
                animeStudio = "No Data";
            }

            animeYear = data.get("year").toString();
            animeFavorites = data.get("favorites").toString();
            animePopularity = data.get("popularity").toString();

            try {
                JSONArray producers = (JSONArray) data.get("producers");
                for (int i = 0; i < producers.toList().size(); i++) {
                    JSONObject producer = (JSONObject) producers.get(i);
                    animeProducersList.add(producer.get("name").toString());
                }
            } catch (Exception ignored) {}

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
            System.out.println("Response CODE: " + caching.getResponseCode());
        }
        List<String> animeStringLists = new ArrayList<>();

        List<List<String>> animeLists = new ArrayList<>();
        animeLists.add(animeProducersList);
        animeLists.add(animeGenresList);
        animeLists.add(animeLicensorList);

        for (List<String> animeList : animeLists) {
            StringBuilder tempString = new StringBuilder();
            for (int j = 0; j < animeList.size(); j++) {
                tempString.append(animeList.get(j));
                if (j != animeList.size() - 1) tempString.append(", ");
            }
            animeStringLists.add(tempString.toString());
        }

        animeProducers = animeStringLists.get(0);
        animeGenres = animeStringLists.get(1);
        animeLicensors = animeStringLists.get(2);

        return List.of(
                new AnimeInformation(animeID, animeTitle, animeImageURL, animeSynopsis, animeMyAnimeListURL, animeTitleEnglish,
                        animeScore, animeScoredBy, animeRank, animeType, animeDuration, animeRating, animeBackground, airing,
                        animeStudio, animeYear, animeFavorites, animePopularity, animeProducers, animeGenres,
                        animeTrailer, animeEpisodes, animeLicensors)
        );
    }
}
