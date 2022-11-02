package com.wang.onlineexam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamRestTest {
    @Autowired
    private MockMvc mockMvc;

    // test post for url: http://localhost:8080/create-blank-paper
    @Test
    public void testCreateBlankPaper() throws Exception {
        ResultActions r = this.mockMvc.perform(post("/create-blank-paper")
                .content("{\"examId\":1,\"paramList\":[{\"questionId\":1,\"order\":1,\"mark\":5},{\"questionId\":2,\"order\":2,\"mark\":3},{\"questionId\":3,\"order\":3,\"mark\":2}]}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"));
        r = r.andDo(print()).andExpect(status().isOk());
    }
}
