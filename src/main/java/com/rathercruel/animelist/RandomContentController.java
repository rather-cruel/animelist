package com.rathercruel.animelist;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Rather Cruel
 */
@Controller
public class RandomContentController {

    @GetMapping("/{content}/random")
    public String randomContent(@PathVariable("content") String content) throws IOException {
        URL url = new URL("https://api.jikan.moe/v4/random/" + content);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.setRequestMethod("GET");
        String contentID = "";

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject data = (JSONObject) jsonObject.get("data");
            contentID = data.get("mal_id").toString();
        } else
            System.out.println("Response CODE: " + responseCode);
        return "redirect:/" + content + "/view/" + contentID;
    }
}
