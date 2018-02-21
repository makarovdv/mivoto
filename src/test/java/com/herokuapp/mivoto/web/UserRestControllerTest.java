package com.herokuapp.mivoto.web;

import org.junit.Test;
import org.springframework.http.MediaType;

import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANTS_PAGE2_TO;
import static com.herokuapp.mivoto.TestUtil.contentJson;
import static com.herokuapp.mivoto.TestUtil.userHttpBasic;
import static com.herokuapp.mivoto.UserTestData.USER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class UserRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserRestController.REST_URL + '/';

    @Override
    protected String getRestUrl() {
        return REST_URL;
    }

    @Test
    public void testGetPage() throws Exception {
        mockMvc.perform(get(REST_URL + "restaurants/by?page=1")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANTS_PAGE2_TO));
    }
}
