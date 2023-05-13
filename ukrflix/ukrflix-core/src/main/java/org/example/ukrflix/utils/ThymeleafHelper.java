package org.example.ukrflix.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ThymeleafHelper {
    private static ThymeleafHelper thymeleafHelper;

    public static ThymeleafHelper getInstance() {
        if (thymeleafHelper == null) {
            thymeleafHelper = new ThymeleafHelper();
        }
        return thymeleafHelper;
    }

    public String readJsonFile(String jsonDescriptions, String locale) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> descriptions = null;
        try {
            descriptions = mapper.readValue(jsonDescriptions, new TypeReference<Map<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(descriptions==null)return null;
        return descriptions.getOrDefault(locale, descriptions.get("en"));
    }
}
