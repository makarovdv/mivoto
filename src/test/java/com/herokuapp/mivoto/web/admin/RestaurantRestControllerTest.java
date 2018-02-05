package com.herokuapp.mivoto.web.admin;

import com.herokuapp.mivoto.RestaurantTestData;
import com.herokuapp.mivoto.TestUtil;
import com.herokuapp.mivoto.model.Restaurant;
import com.herokuapp.mivoto.service.RestaurantService;
import com.herokuapp.mivoto.to.RestaurantTo;
import com.herokuapp.mivoto.web.json.JsonUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.herokuapp.mivoto.RestaurantTestData.*;
import static com.herokuapp.mivoto.TestUtil.contentJson;
import static com.herokuapp.mivoto.TestUtil.userHttpBasic;
import static com.herokuapp.mivoto.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractAdminRestControllerTest{

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    RestaurantService restaurantService;

    @Override
    protected String getRestUrl() {
        return REST_URL;
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant created = RestaurantTestData.getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

        RestaurantTo returned = TestUtil.readFromJson(action, RestaurantTo.class);
        Integer id = returned.getId();
        created.setId(id);

        assertMatch(returned, created);
        assertMatch(restaurantService.get(id), created);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = UPDATED_TERRA_MARE;
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(restaurantService.getPage(1).getContent(), POROSELLO, SALOTTO);
    }

    @Test
    public void testGetPageWithMenu() throws Exception {
            mockMvc.perform(get(REST_URL + "menu/by?page=1&date=2017-12-30")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANTS_PAGE2_TO));
    }
}
