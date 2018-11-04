package ru.popov.book_rent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    private ObjectMapper objectMapper = new ObjectMapper();

    protected String getJsonMessage(String message) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", message);
        try {
            return objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
