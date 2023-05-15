package org.example.ukrflix.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThymeleafHelper {
    private static final Logger LOGGER = Logger.getLogger(ThymeleafHelper.class);

    public String readJsonFile(String jsonDescriptions, String locale) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> descriptions = null;
        try {
            descriptions = mapper.readValue(jsonDescriptions, new TypeReference<Map<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("we have error", e);
        }
        if (descriptions == null) {
            return null;
        }
        return descriptions.getOrDefault(locale, descriptions.get("en"));
    }
}
