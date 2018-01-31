package com.herokuapp.mivoto.web.admin;

import com.herokuapp.mivoto.web.AbstractControllerTest;
import org.junit.Test;

import static com.herokuapp.mivoto.TestUtil.userHttpBasic;
import static com.herokuapp.mivoto.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

abstract public class AbstractAdminRestControllerTest extends AbstractControllerTest {
    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(getRestUrl())
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }
}
