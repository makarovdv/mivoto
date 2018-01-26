package com.herokuapp.mivoto.web.admin;

import com.herokuapp.mivoto.TestUtil;
import com.herokuapp.mivoto.service.MenuService;
import com.herokuapp.mivoto.to.MenuTo;
import com.herokuapp.mivoto.web.AbstractControllerTest;
import com.herokuapp.mivoto.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import static com.herokuapp.mivoto.DishTestData.CANNELLONI;
import static com.herokuapp.mivoto.DishTestData.THE_PORKIE;
import static com.herokuapp.mivoto.MenuTestData.*;
import static com.herokuapp.mivoto.MenuTestData.assertMatch;
import static com.herokuapp.mivoto.TestUtil.contentJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MenuRestController.REST_URL + '/';

    @Autowired
    MenuService menuService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1));
    }

    @Test
    public void testUpdate() throws Exception {
        MenuTo updated = new MenuTo(MENU1);
        updated.setDate(LocalDate.of(2018, 1, 25));
        updated.setDishes(new HashSet<>(Arrays.asList(THE_PORKIE, CANNELLONI)));
        mockMvc.perform(put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(menuService.get(MENU1_ID), updated);
    }


    @Test
    public void testCreate() throws Exception {
        MenuTo created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        MenuTo returned = TestUtil.readFromJson(action, MenuTo.class);
        Integer id = returned.getId();
        created.setId(id);

        assertMatch(returned, created);
        assertMatch(menuService.get(id), created);
    }
}
