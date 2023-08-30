package com.rathercruel.animelist.manga.manga_list;

import com.rathercruel.animelist.manga.get_manga.GetTopManga;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Rather Cruel
 */
@Controller
public class MangaListController {

    @GetMapping("/manga/page={current_page}")
    public String mangaPages(Model model, @PathVariable("current_page") int currentPage) throws IOException {
        model.addAttribute("site_title", "AnimeList - Manga");
        URL urlObject = new URL("https://api.jikan.moe/v4/top/manga?page=" + currentPage + "&limit=24&sfw");

        GetTopManga getTopManga = new GetTopManga();
        List<Manga> mangaList = getTopManga.getMangaTop(urlObject);

        model.addAttribute("mangaList", mangaList);
        model.addAttribute("pagination_type", "manga");

        model.addAttribute("current_page", currentPage);
        model.addAttribute("total_pages", getTopManga.getTotalPages());

        if (currentPage > getTopManga.getTotalPages())
            return "redirect:/manga/page=" + getTopManga.getTotalPages();
        else if (currentPage < 1)
            return "redirect:/manga/page=1";
        else
            return "manga/manga";
    }
}
