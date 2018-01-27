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
import static com.herokuapp.mivoto.TestUtil.userHttpBasic;
import static com.herokuapp.mivoto.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .content(JsonUtil.writeValue(created)));

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
        System.out.println("delete" + REST_URL + RESTAURANT1_ID);
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        assertMatch(restaurantService.getPage(2), POROSELLO, SALOTTO);
    }
}