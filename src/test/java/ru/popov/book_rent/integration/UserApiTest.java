package ru.popov.book_rent.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserApiTest extends ApiTest {

    @Autowired
    public void setMvcMock(MockMvc mvc){
        this.mvc = mvc;
    }

    @Test
    public void givenIAmLoggedUserWithIdOne() throws Exception {

        String accessToken = getUserToken(DEFAULT_USER, DEFAULT_USER_PASSWORD);

        this.mvc.perform(get("/users/1/books")
                .header(AUTH_HEADER, accessToken))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

    @Test
    public void givenIAmLoggedUserWithIdTwoAndTryToReadBooksOfAnotherUser() throws Exception {

        String accessToken = getUserToken(DEFAULT_USER, DEFAULT_USER_PASSWORD);

        this.mvc.perform(get("/users/2/books")
                .header(AUTH_HEADER, accessToken))
                .andExpect(status().isForbidden());
    }

    @Test
    public void givenIHaveClientTokenAndTryToReadBooksOfUser() throws Exception {

        String accessToken = getClientToken();

        this.mvc.perform(get("/users/1/books")
                .header(AUTH_HEADER, accessToken))
                .andExpect(status().isForbidden());
    }



}
