package com.intuit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.comm.MyAggregatedHubRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SupportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMyAggregatedHubTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MyAggregatedHubRequest myAggregatedHubRequest = new MyAggregatedHubRequest();

        this.mockMvc.perform(get("/myAggregatedHub")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(myAggregatedHubRequest)))
                .andDo(print()).andExpect(status().isOk());
    }
}