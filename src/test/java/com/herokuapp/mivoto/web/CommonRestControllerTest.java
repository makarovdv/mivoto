package com.herokuapp.mivoto.web;

import org.junit.Test;
import org.springframework.http.MediaType;

import static com.herokuapp.mivoto.RestaurantTestData.RESTAURANTS_PAGE2_TO;
import static com.herokuapp.mivoto.TestUtil.contentJson;
import static com.herokuapp.mivoto.TestUtil.userHttpBasic;
import static com.herokuapp.mivoto.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class CommonRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = CommonRestController.REST_URL + '/';

    @Override
    protected String getRestUrl() {
        return REST_URL;
    }

    @Test
    public void testGetPage() throws Exception {
        mockMvc.perform(get(REST_URL + "by?page=1")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANTS_PAGE2_TO));
    }
}
