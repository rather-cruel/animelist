package com.rathercruel.animelist.manga.manga_page;

import com.rathercruel.animelist.anime.anime_page.RecommendedAnime;
import com.rathercruel.animelist.anime.anime_page.RelatedContent;
import com.rathercruel.animelist.anime.get_anime.GetRecommended;
import com.rathercruel.animelist.anime.get_anime.GetRelatedContent;
import com.rathercruel.animelist.manga.get_manga.GetManga;
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

        List<MangaInformation> mangaInformationList = manga.getManga(url);

        List<RecommendedAnime> mangaList;
        List<RelatedContent> relatedContentList;

        GetRecommended getRecommended = new GetRecommended();
        GetRelatedContent getRelatedContent = new GetRelatedContent();
        mangaList = getRecommended.getAnimeRecommendations(urlRecommendations);
        relatedContentList = getRelatedContent.getRelatedContent(url);
        model.addAttribute("content_kind", "manga");
        model.addAttribute("content_list", mangaList);
        model.addAttribute("content_information", mangaInformationList.get(0));
        model.addAttribute("content_relations", relatedContentList);
        return "content/content-page";
    }
}
