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
    public String animePage(Model model, @PathVariable("manga_id") String mangaID) throws IOException {
        URL url = new URL("https://api.jikan.moe/v4/manga/" + mangaID + "/full");
        URL urlRecommendations = new URL("https://api.jikan.moe/v4/manga/" + mangaID + "/recommendations");
        GetManga manga = new GetManga();

        List<MangaInformation> mangaInformationList = manga.getManga(url);

        List<RecommendedAnime> animeList;
        List<RelatedContent> relatedContentList;

        GetRecommended getRecommended = new GetRecommended();
        GetRelatedContent getRelatedContent = new GetRelatedContent();

        relatedContentList = getRelatedContent.getRelatedContent(url);
        animeList = getRecommended.getAnimeRecommendations(urlRecommendations);
        model.addAttribute("anime_list", animeList);
        model.addAttribute("anime_information", mangaInformationList.get(0));
        model.addAttribute("anime_relations", relatedContentList);
        return "manga/manga-page";
    }
}
