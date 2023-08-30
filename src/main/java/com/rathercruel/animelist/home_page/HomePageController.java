package com.rathercruel.animelist.home_page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rather Cruel
 */
@Controller
public class HomePageController {

    @GetMapping({"/index", "/index/"})
    public String homeRedirect() {
        return "redirect:/";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("site_title", "AnimeList");
        return "index";
    }
}
