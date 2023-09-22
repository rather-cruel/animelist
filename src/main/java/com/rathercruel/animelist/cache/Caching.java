package com.rathercruel.animelist.cache;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import static com.rathercruel.animelist.AnimelistApplication.*;

/**
 * @author Rather Cruel
 */
public class Caching {
    private int responseCode;
    private URL currentURL;
    private int animeID;
    private String type;
    private String kind;

    public Caching(URL currentURL, int animeID, String type, String kind) {
        this.currentURL = currentURL;
        this.animeID = animeID;
        this.type = type;
        this.kind = kind;
    }

    public JSONObject connection() throws IOException {
        HashMap<Integer, JSONObject> connectionHashMap;
        if (type.equals("anime")) {
            if (kind.equals("recommendations"))
                connectionHashMap = cacheHashMapAnimeRecommendations;
            else if (kind.equals("related"))
                connectionHashMap = cacheHashMapAnimeRelated;
            else
                connectionHashMap = cacheHashMapAnime;
        } else {
            if (kind.equals("recommendations"))
                connectionHashMap = cacheHashMapMangaRecommendations;
            else if (kind.equals("related"))
                connectionHashMap = cacheHashMapMangaRelated;
            else
                connectionHashMap = cacheHashMapManga;
        }

        if (connectionHashMap.containsKey(animeID)) {
            System.out.println("Took cache from the HashMap!");

            JSONObject jsonObject = connectionHashMap.get(animeID);
            responseCode = HttpsURLConnection.HTTP_OK;
            return jsonObject;
        } else {
            System.out.println("Made a request to API!");

            HttpsURLConnection connection = (HttpsURLConnection) currentURL.openConnection();
            connection.setRequestMethod("GET");
            responseCode = connection.getResponseCode();

            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            connectionHashMap.put(animeID, jsonObject);
            return jsonObject;
        }
    }

    public int getResponseCode() {
        return responseCode;
    }
}
