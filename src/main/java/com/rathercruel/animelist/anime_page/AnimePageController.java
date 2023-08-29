package com.rathercruel.animelist.anime_page;

import com.rathercruel.animelist.anime_list.Anime;
import com.rathercruel.animelist.get_anime.GetAnime;
import com.rathercruel.animelist.get_anime.GetRecommended;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rather Cruel
 */
@Controller
public class AnimePageController {

    @GetMapping("/anime/view/{anime_id}")
    public String animePage(Model model, @PathVariable("anime_id") String animeID) throws IOException {
//        0 animeID
//        1 animeTitle
//        2 animeImageURL
//        3 animeSynopsis
//        4 animeMyAnimeListURL
//        5 animeTitleEnglish
//        6 animeScore
//        7 animeScoredBy
//        8 animeRank
//        9 animeType
//        10 animeDuration
//        11 animeRating
//        12 animeBackground
//        13 airing
//        14 animeStudio
//        15 animeYear
//        16 animeFavorites
//        17 animePopularity
//        18 animeProducers
//        19 animeGenres

        URL url = new URL("https://api.jikan.moe/v4/anime/" + animeID);
        URL urlRecommendations = new URL("https://api.jikan.moe/v4/anime/" + animeID + "/recommendations");
        GetAnime getAnime = new GetAnime();
        List<String> arr = getAnime.getAnime(url);
        List<RecommendedAnime> animeList = new ArrayList<>();

        GetRecommended getRecommended = new GetRecommended();
        getRecommended.getAnimeRecommendations(urlRecommendations, animeList);
        model.addAttribute("anime_list", animeList);

        model.addAttribute("page_title", "AnimeList - " + arr.get(1));
        model.addAttribute("anime_title", arr.get(1));
        model.addAttribute("anime_image", arr.get(2));
        model.addAttribute("anime_desc", arr.get(3));
        model.addAttribute("anime_myAnimeList", arr.get(4));
        model.addAttribute("anime_english_title", arr.get(5));

        model.addAttribute("anime_score", arr.get(6));
        model.addAttribute("anime_scored_by", arr.get(7));
        model.addAttribute("anime_rank", arr.get(8));
        model.addAttribute("anime_type", arr.get(9));
        model.addAttribute("anime_duration", arr.get(10));
        model.addAttribute("anime_rating", arr.get(11));
        model.addAttribute("anime_background", arr.get(12));
        model.addAttribute("anime_airing", arr.get(13));
        model.addAttribute("anime_studio", arr.get(14));
        model.addAttribute("anime_year", arr.get(15));
        model.addAttribute("anime_favorites", arr.get(16));
        model.addAttribute("anime_popularity", arr.get(17));
        model.addAttribute("anime_producers", arr.get(18));
        model.addAttribute("anime_genres", arr.get(19));
        return "anime-page";
    }
}
