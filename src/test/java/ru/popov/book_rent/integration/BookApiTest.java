package ru.popov.book_rent.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookApiTest extends ApiTest {

    @Autowired
    public void setMvc(MockMvc mvc){
        this.mvc = mvc;
    }

    @Test
    public void givenIHaveClientTokenAnTryToGetAllAvailableBooks() throws Exception {

        String clientToken = getClientToken();

        this.mvc.perform(get("/books")
                .header(AUTH_HEADER , clientToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(5)));
    }

    @Test
    public void givenIHaveUserTokenAnTryToGetAllAvailableBooks() throws Exception {

        String userToken = getUserToken(DEFAULT_USER, DEFAULT_USER_PASSWORD);

        this.mvc.perform(get("/books")
                .header(AUTH_HEADER , userToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(5)));
    }

    @Test
    public void givenIHaveNotAnyTokenAnTryToGetAllAvailableBooks() throws Exception {

        this.mvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}
