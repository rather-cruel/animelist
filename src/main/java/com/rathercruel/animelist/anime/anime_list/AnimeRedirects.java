package com.rathercruel.animelist.anime.anime_list;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Rather Cruel
 */
@Controller
public class AnimeRedirects {

    @GetMapping({"/anime", "/anime/", "/anime/page", "/anime/page/", "/anime/page="})
    public String animeRedirect() {
        return "redirect:/anime/page=1";
    }

    @GetMapping("/anime/{id}")
    public String animePageRedirect(@PathVariable("id") int id) {
        return "redirect:/anime/view/" + id;
    }
}
