package com.rathercruel.animelist.anime.anime_page;

import com.rathercruel.animelist.anime.get_anime.GetAnime;
import com.rathercruel.animelist.anime.get_anime.GetRecommended;
import com.rathercruel.animelist.anime.get_anime.GetRelatedContent;
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
public class AnimePageController {

    @GetMapping("/anime/view/{anime_id}")
    public String animePage(Model model, @PathVariable("anime_id") String animeID) throws IOException {
        URL url = new URL("https://api.jikan.moe/v4/anime/" + animeID + "/full");
        URL urlRecommendations = new URL("https://api.jikan.moe/v4/anime/" + animeID + "/recommendations");
        GetAnime anime = new GetAnime();
        int intAnimeId = Integer.parseInt(animeID);

        List<AnimeInformation> animeInformationList = anime.getAnime(url, intAnimeId);
        List<RecommendedAnime> animeList;
        List<RelatedContent> relatedContentList;
        GetRecommended getRecommended = new GetRecommended();
        GetRelatedContent getRelatedContent = new GetRelatedContent();
        animeList = getRecommended.getAnimeRecommendations(urlRecommendations, intAnimeId);
        relatedContentList = getRelatedContent.getRelatedContent(url, intAnimeId, "anime");

        model.addAttribute("active_link", "Anime");
        model.addAttribute("content_kind", "anime");
        model.addAttribute("content_list", animeList);
        model.addAttribute("content_information", animeInformationList.get(0));
        model.addAttribute("content_relations", relatedContentList);
        return "content/content-page";
    }
}
