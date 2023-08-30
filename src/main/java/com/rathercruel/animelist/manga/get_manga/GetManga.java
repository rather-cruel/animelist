package com.rathercruel.animelist.manga.get_manga;

import com.rathercruel.animelist.manga.manga_page.MangaInformation;
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
public class GetManga {
    public List<MangaInformation> getManga(URL urlObject) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

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
        String mangaGenres = "";

        List<String> mangaGenresList = new ArrayList<>();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject data = (JSONObject) jsonObject.get("data");

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
                mangaGenres += mangaGenresList.get(i);
                if (i != mangaGenresList.size() - 1) mangaGenres += ", ";
            }
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
        return List.of(
                new MangaInformation(mangaID, mangaMyAnimeListURL, mangaImage, mangaTitle, mangaTitleEnglish,
                        mangaType, mangaChapters, mangaVolumes, mangaStatus, mangaPublished,
                        mangaScore, mangaScoredBy, mangaRank, mangaPopularity, mangaMembers,
                        mangaFavorites, mangaSynopsis, mangaBackground, mangaAuthors, mangaGenres));
    }
}
