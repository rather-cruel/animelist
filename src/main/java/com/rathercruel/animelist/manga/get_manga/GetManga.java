package com.rathercruel.animelist.manga.get_manga;

import com.rathercruel.animelist.cache.Caching;
import com.rathercruel.animelist.manga.models.MangaInformation;
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
public class GetManga {
    public List<MangaInformation> getManga(URL urlObject, int intMangaID) throws IOException {
        Caching caching = new Caching(urlObject, intMangaID, "manga", "basic");
        JSONObject data = caching.connection().getJSONObject("data");

        String mangaID = "";
        String mangaMyAnimeListURL = "";
        String mangaImage = "";
        String mangaTitle = "";
        String mangaTitleEnglish = "";
        String mangaType = "";
        String mangaChapters = "";
        String mangaVolumes = "";
        String mangaStatus = "";
        String mangaPublished = "";
        String mangaScore = "";
        String mangaScoredBy = "";
        String mangaRank = "";
        String mangaPopularity = "";
        String mangaMembers = "";
        String mangaFavorites = "";
        String mangaSynopsis = "";
        String mangaBackground = "";
        String mangaAuthors = "";
        StringBuilder mangaGenres = new StringBuilder();

        List<String> mangaGenresList = new ArrayList<>();

        if (caching.getResponseCode() == HttpsURLConnection.HTTP_OK || caching.getResponseCode() == 0) {
            mangaID = data.get("mal_id").toString();
            mangaMyAnimeListURL = data.get("url").toString();

            JSONObject images = (JSONObject) data.get("images");
            JSONObject jpg = (JSONObject) images.get("jpg");

            if (!jpg.isNull("large_image_url")) {
                mangaImage = (String) jpg.get("large_image_url");
            } else {
                if (!jpg.isNull("image_url"))
                    mangaImage = (String) jpg.get("image_url");
                else if (!jpg.isNull("small_image_url"))
                    mangaImage = (String) jpg.get("small_image_url");
                else
                    mangaImage = "https://placehold.co/424x600?text=No+Image";
            }

            mangaTitle = data.get("title").toString();
            mangaType = data.get("type").toString();
            mangaChapters = data.get("chapters").toString();
            mangaVolumes = data.get("volumes").toString();
            mangaStatus = data.get("status").toString();

            JSONObject published = (JSONObject) data.get("published");
            mangaPublished = published.get("string").toString();

            mangaScore = data.get("score").toString();
            mangaScoredBy = data.get("scored_by").toString();
            mangaRank = data.get("rank").toString();
            mangaPopularity = data.get("popularity").toString();
            mangaMembers = data.get("members").toString();
            mangaFavorites = data.get("favorites").toString();
            mangaBackground = data.get("background").toString();

            JSONArray authors = (JSONArray) data.get("authors");
            JSONObject author = (JSONObject) authors.get(0);
            mangaAuthors = author.get("name").toString();

            if (!data.isNull("title_english"))
                mangaTitleEnglish = (String) data.get("title_english");

            if (!data.isNull("synopsis"))
                mangaSynopsis = (String) data.get("synopsis");

            JSONArray genres = (JSONArray) data.get("genres");
            for (int i = 0; i < genres.toList().size(); i++) {
                JSONObject genre = (JSONObject) genres.get(i);
                mangaGenresList.add(genre.get("name").toString());
            }

            for (int i = 0; i < mangaGenresList.size(); i++) {
                mangaGenres.append(mangaGenresList.get(i));
                if (i != mangaGenresList.size() - 1) mangaGenres.append(", ");
            }
        } else {
            System.out.println("Response CODE: " + caching.getResponseCode());
        }
        return List.of(
                new MangaInformation(mangaID, mangaMyAnimeListURL, mangaImage, mangaTitle, mangaTitleEnglish,
                        mangaType, mangaChapters, mangaVolumes, mangaStatus, mangaPublished,
                        mangaScore, mangaScoredBy, mangaRank, mangaPopularity, mangaMembers,
                        mangaFavorites, mangaSynopsis, mangaBackground, mangaAuthors, mangaGenres.toString()));
    }
}
