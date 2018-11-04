package ru.popov.book_rent.integration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.h2.tools.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.popov.book_rent.BookRentApplication;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BookRentApplication.class)
@AutoConfigureMockMvc
@Transactional
public abstract class ApiTest {

    private final String GRANT_TYPE_PASSWORD = "password";
    private final String GRANT_TYPE_CLIENT = "client_credentials";
    private final String BEARER = "Bearer ";
    private final String ACCESS_TOKEN = "access_token";
    private final String AUTH_ENDPOINT = "/oauth/token";
    private final String CLIENT_ID = "test";
    private final String CLIENT_SECRET = "test";

    protected final String AUTH_HEADER = "Authorization";
    protected final String DEFAULT_USER = "gorge39@gmail.com";
    protected final String DEFAULT_USER_PASSWORD = "testtest";

    protected MockMvc mvc;

    private Server webServer;

    protected void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }

    protected String getUserToken(String username, String password) throws Exception {

        MultiValueMap<String, String> authRequest = new LinkedMultiValueMap<>();
        authRequest.add("grant_type", GRANT_TYPE_PASSWORD);
        authRequest.add("client_id", CLIENT_ID);
        authRequest.add("username", username);
        authRequest.add("password", password);

        return performRequest(authRequest);
    }

    protected String getClientToken() throws Exception {

        MultiValueMap<String, String> authRequest = new LinkedMultiValueMap<>();
        authRequest.add("grant_type", GRANT_TYPE_CLIENT);
        authRequest.add("client_id", CLIENT_ID);
        authRequest.add("client_secret", CLIENT_SECRET);

        return performRequest(authRequest);
    }

    private String performRequest(MultiValueMap<String, String> authRequest) throws Exception {
        ResultActions result = this.mvc.perform(post(AUTH_ENDPOINT)
                        .params(authRequest)
                        .with(httpBasic(CLIENT_ID,CLIENT_SECRET))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        String response = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return BEARER + jsonParser.parseMap(response).get("access_token").toString();
    }


}
