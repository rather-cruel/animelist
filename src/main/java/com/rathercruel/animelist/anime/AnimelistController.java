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
    private int totalPages = 0;

    public int getTotalPages() {
        return totalPages;
    }

    private void printAnime(URL urlObject, List<Anime> animeList) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

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

                animeList.add(new Anime(animeTitle, animeImageURL, animeSynopsis, animeMyAnimeListURL, animeTitleEnglish));
            }
        } else {
            System.out.println("Response CODE: " + responseCode);
        }
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("site_title", "AnimeList");
        return "index";
    }

    @GetMapping("/anime/search/{search}")
    public String animeSearchPage(Model model, @PathVariable String search) throws IOException {
        model.addAttribute("text", search);
        String siteTitle = "AnimeList - " + search;
        model.addAttribute("site_title", siteTitle);

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

    @GetMapping("/anime/page={current_page}&limit=24")
    public String animePages(Model model, @PathVariable("current_page") int currentPage) throws IOException {
        model.addAttribute("site_title", "AnimeList - Anime");
        URL urlObject = new URL("https://api.jikan.moe/v4/top/anime?page=" + currentPage + "&limit=24");
        List<Anime> animeList = new ArrayList<>();

        printAnime(urlObject, animeList);
        model.addAttribute("animeList", animeList);

        model.addAttribute("current_page", currentPage);
        model.addAttribute("total_pages", totalPages);

        if (currentPage > totalPages)
            return "redirect:/anime/page=" + totalPages + "&limit=24";
        else if (currentPage < 1)
            return "redirect:/anime/page=1&limit=24";
        else
            return "anime";
    }
}
