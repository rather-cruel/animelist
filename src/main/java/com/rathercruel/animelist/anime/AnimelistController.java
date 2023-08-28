package com.rathercruel.animelist.anime;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rather Cruel
 */
@Controller
public class AnimelistController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    private void printAnime(URL urlObject, List<Anime> animeList) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        String animeTitle = "";
        String animeImageURL = "";
        String animeSynopsis = "";
        String animeMyAnimeListURL = "";
        String animeJapaneseName = "";

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray data = (JSONArray) jsonObject.get("data");

            for (int i = 0; i < data.toList().size(); i++) {
                JSONObject dataIndex = (JSONObject) data.get(i);
                JSONObject images = (JSONObject) dataIndex.get("images");
                JSONObject jpg = (JSONObject) images.get("jpg");

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

                if (!dataIndex.isNull("title_japanese"))
                    animeJapaneseName = (String) dataIndex.get("title_japanese");

                if (!dataIndex.isNull("synopsis"))
                    animeSynopsis = (String) dataIndex.get("synopsis");

                animeList.add(new Anime(animeTitle, animeImageURL, animeSynopsis, animeMyAnimeListURL, animeJapaneseName));
            }
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
    }

    @GetMapping("/anime")
    public String animePage(Model model) throws IOException {
        URL urlObject = new URL("https://api.jikan.moe/v4/top/anime");
        List<Anime> animeList = new ArrayList<>();

        printAnime(urlObject, animeList);
        model.addAttribute("animeList", animeList);
        return "anime";
    }

    @GetMapping("/anime/search/{search}")
    public String animeSearchPage(Model model, @PathVariable String search) throws IOException {
        model.addAttribute("text", search);

        String[] stringArray = search.split(" ");
        StringBuilder link = new StringBuilder();

        for (String s : stringArray) {
            link.append(s).append("%20");
        }

        URL urlObject = new URL("https://api.jikan.moe/v4/anime?q=" + link + "&sfw");
        List<Anime> animeList = new ArrayList<>();

        printAnime(urlObject, animeList);
        model.addAttribute("animeList", animeList);

        if (!animeList.isEmpty())
            return "anime-search";
        else
            return "search-not-found";
    }

    @PostMapping("/anime/search")
    public String animeSearchAction(Model model, @RequestParam String search) {
        return "redirect:/anime/search/" + search;
    }
}
