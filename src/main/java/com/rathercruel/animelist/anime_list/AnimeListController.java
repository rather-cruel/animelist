package com.rathercruel.animelist.anime_list;

import com.rathercruel.animelist.get_anime.GetTop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rather Cruel
 */
@Controller
public class AnimeListController {

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

        GetTop getTop = new GetTop();
        getTop.getAnimeTop(urlObject, animeList);
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

        GetTop getTop = new GetTop();
        getTop.getAnimeTop(urlObject, animeList);
        model.addAttribute("animeList", animeList);

        model.addAttribute("current_page", currentPage);
        model.addAttribute("total_pages", getTop.getTotalPages());

        if (currentPage > getTop.getTotalPages())
            return "redirect:/anime/page=" + getTop.getTotalPages() + "&limit=24";
        else if (currentPage < 1)
            return "redirect:/anime/page=1&limit=24";
        else
            return "anime";
    }
}
