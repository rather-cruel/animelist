package com.rathercruel.animelist.anime.anime_list;

import com.rathercruel.animelist.anime.get_anime.GetTopAnime;
import com.rathercruel.animelist.manga.get_manga.GetTopManga;
import com.rathercruel.animelist.manga.manga_list.Manga;
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

        URL animeUrlObject = new URL("https://api.jikan.moe/v4/anime?q=" + link + "&sfw");
        URL mangaUrlObject = new URL("https://api.jikan.moe/v4/manga?q=" + link + "&sfw");
        GetTopAnime getTopAnime = new GetTopAnime();
        GetTopManga getTopManga = new GetTopManga();

        List<Anime> animeList = getTopAnime.getAnimeTop(animeUrlObject);
        List<Manga> mangaList = getTopManga.getMangaTop(mangaUrlObject);

        model.addAttribute("animeList", animeList);
        model.addAttribute("mangaList", mangaList);

        if (!animeList.isEmpty())
            return "content/content-search";
        else
            return "search-not-found";
    }

    @PostMapping("/anime/search")
    public String animeSearchAction(Model model, @RequestParam String search) {
        return "redirect:/anime/search/" + search;
    }

    @GetMapping("/anime/page={current_page}")
    public String animePages(Model model, @PathVariable("current_page") int currentPage) throws IOException {
        model.addAttribute("site_title", "AnimeList - Anime");
        URL urlObject = new URL("https://api.jikan.moe/v4/top/anime?page=" + currentPage + "&limit=24&sfw");
        GetTopAnime getTopAnime = new GetTopAnime();

        List<Anime> animeList = getTopAnime.getAnimeTop(urlObject);

        model.addAttribute("active_link", "Anime");
        model.addAttribute("content_type", "anime");
        model.addAttribute("content_h1", "Anime");
        model.addAttribute("content_list", animeList);
        model.addAttribute("pagination_type", "anime");

        model.addAttribute("current_page", currentPage);
        model.addAttribute("total_pages", getTopAnime.getTotalPages());

        if (currentPage > getTopAnime.getTotalPages())
            return "redirect:/anime/page=" + getTopAnime.getTotalPages();
        else if (currentPage < 1)
            return "redirect:/anime/page=1";
        else
            return "content/content";
    }
}















