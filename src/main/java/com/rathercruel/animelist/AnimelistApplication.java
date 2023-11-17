package com.rathercruel.animelist;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

// ratherKRUEL - login
// ratherKRUEL1 - passwd

@SpringBootApplication
public class AnimelistApplication {

	public static HashMap<Integer, JSONObject> cacheHashMapAnime = new HashMap<>();
	public static HashMap<Integer, JSONObject> cacheHashMapAnimeRelated = new HashMap<>();
	public static HashMap<Integer, JSONObject> cacheHashMapAnimeRecommendations = new HashMap<>();

	public static HashMap<Integer, JSONObject> cacheHashMapManga = new HashMap<>();
	public static HashMap<Integer, JSONObject> cacheHashMapMangaRelated = new HashMap<>();
	public static HashMap<Integer, JSONObject> cacheHashMapMangaRecommendations = new HashMap<>();

	public static HashMap<Integer, JSONObject> topAnimeHashMap = new HashMap<>();
	public static HashMap<Integer, JSONObject> topMangaHashMap = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(AnimelistApplication.class, args);
	}
}
