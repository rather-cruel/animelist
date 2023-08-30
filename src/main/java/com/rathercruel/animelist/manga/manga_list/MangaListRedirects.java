package com.rathercruel.animelist.manga.manga_list;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Rather Cruel
 */
@Controller
public class MangaListRedirects {

    @GetMapping({"/manga", "/manga/", "/manga/page", "/manga/page/", "/manga/page="})
    public String mangaRedirect() {
        return "redirect:/manga/page=1";
    }

    @GetMapping("/manga/{id}")
    public String mangaPageRedirect(@PathVariable("id") int id) {
        return "redirect:/manga/view/" + id;
    }
}
