package ru.popov.book_rent.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookRentTest extends ApiTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setMvc(MockMvc mvc){
        this.mvc = mvc;
    }

    @Test
    public void giveIHaveClientAndTryToRentBook() throws Exception {
        String clientToken = getClientToken();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("book_id", 1);
        requestBody.put("duration", 3);

        this.mvc.perform(post("/rent")
                .header(AUTH_HEADER , clientToken)
                .content(objectMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    public void giveIHaveUserAndTryToRentBook() throws Exception {
        String userToken = getUserToken(DEFAULT_USER, DEFAULT_USER_PASSWORD);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("book_id", 2);
        requestBody.put("duration", 8);

        this.mvc.perform(post("/rent")
                .header(AUTH_HEADER , userToken)
                .content(objectMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        Map<String, Object> requestBody2 = new HashMap<>();
        requestBody.put("book_id", 3);
        requestBody.put("duration", 8);

        // And now user shouldn't have enough money
        this.mvc.perform(post("/rent")
                .header(AUTH_HEADER , userToken)
                .content(objectMapper.writeValueAsString(requestBody2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void givenWeTrySendNotValidRequest() throws Exception {
        String userToken = getUserToken(DEFAULT_USER, DEFAULT_USER_PASSWORD);

        Map<String, Object> requestBody = new HashMap<>();

        this.mvc.perform(post("/rent")
                .header(AUTH_HEADER , userToken)
                .content(objectMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }



}
