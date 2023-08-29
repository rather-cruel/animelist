package com.rathercruel.animelist.anime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Rather Cruel
 */
@Controller
public class AnimeRedirects {

    @GetMapping("/index")
    public String homeRedirect() {
        return "redirect:/";
    }

    @GetMapping("/index/")
    public String homeRedirect2() {
        return "redirect:/";
    }

    @GetMapping("/anime")
    public String animeRedirect() {
        return "redirect:/anime/page=1&limit=24";
    }

    @GetMapping("/anime/")
    public String animeRedirect2() {
        return "redirect:/anime/page=1&limit=24";
    }

    @GetMapping("/anime/page")
    public String animeRedirect3() {
        return "redirect:/anime/page=1&limit=24";
    }

    @GetMapping("/anime/page/")
    public String animeRedirect4() {
        return "redirect:/anime/page=1&limit=24";
    }

    @GetMapping("/anime/page=")
    public String animeRedirect5() {
        return "redirect:/anime/page=1&limit=24";
    }

    @GetMapping("/anime/page={current_page}")
    public String animeRedirectLimit(@PathVariable("current_page") int currentPage) {
        AnimelistController animelistController = new AnimelistController();
        if (currentPage > animelistController.getTotalPages())
            return "redirect:/anime/page" + animelistController.getTotalPages() + "&limit=24";
        else
            return "redirect:/anime/page=" + currentPage + "&limit=24";
    }
}
