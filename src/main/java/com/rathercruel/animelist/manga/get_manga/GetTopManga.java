package com.rathercruel.animelist.manga.get_manga;

import com.rathercruel.animelist.manga.models.Manga;
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
public class GetTopManga {
    private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public List<Manga> getMangaTop(URL urlObject) throws IOException {
        List<Manga> mangaList = new ArrayList<>();
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        String mangaID;
        String mangaTitle;
        String mangaImage;
        String mangaSynopsis = "";
        String mangaMyAnimeListURL;
        String mangaTitleEnglish = "";

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

                mangaID = dataIndex.get("mal_id").toString();

                mangaMyAnimeListURL = (String) dataIndex.get("url");
                mangaTitle = (String) dataIndex.get("title");

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

                if (!dataIndex.isNull("title_english"))
                    mangaTitleEnglish = (String) dataIndex.get("title_english");

                if (!dataIndex.isNull("synopsis"))
                    mangaSynopsis = (String) dataIndex.get("synopsis");

                mangaList.add(new Manga(mangaID, mangaTitle, mangaImage, mangaSynopsis, mangaMyAnimeListURL, mangaTitleEnglish));
            }
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
        return mangaList;
    }
}
