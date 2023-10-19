package com.rathercruel.animelist.manga.manga_page;

import com.rathercruel.animelist.anime.models.RecommendedAnime;
import com.rathercruel.animelist.anime.models.RelatedContent;
import com.rathercruel.animelist.anime.get_anime.GetRecommended;
import com.rathercruel.animelist.anime.get_anime.GetRelatedContent;
import com.rathercruel.animelist.manga.get_manga.GetManga;
import com.rathercruel.animelist.manga.models.MangaInformation;
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
public class MangaPageController {

    @GetMapping("/manga/view/{manga_id}")
    public String mangaPage(Model model, @PathVariable("manga_id") String mangaID) throws IOException {
        URL url = new URL("https://api.jikan.moe/v4/manga/" + mangaID + "/full");
        URL urlRecommendations = new URL("https://api.jikan.moe/v4/manga/" + mangaID + "/recommendations");
        GetManga manga = new GetManga();

        int intMangaID = Integer.parseInt(mangaID);
        List<MangaInformation> mangaInformationList = manga.getManga(url, intMangaID);

        List<RecommendedAnime> mangaList;
        List<RelatedContent> relatedContentList;

        GetRecommended getRecommended = new GetRecommended();
        GetRelatedContent getRelatedContent = new GetRelatedContent();
        mangaList = getRecommended.getAnimeRecommendations(urlRecommendations, intMangaID);
        relatedContentList = getRelatedContent.getRelatedContent(url, intMangaID, "manga");
        model.addAttribute("active_link", "Manga");
        model.addAttribute("content_kind", "manga");
        model.addAttribute("content_list", mangaList);
        model.addAttribute("content_information", mangaInformationList.get(0));
        model.addAttribute("content_relations", relatedContentList);
        return "content/content-page";
    }
}
