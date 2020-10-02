package com.intuit;

import com.intuit.client.BananaWebClient;
import com.intuit.client.StrawberryWebClient;
import com.intuit.comm.MyAggregatedHubRequest;
import com.intuit.comm.MyAggregatedHubResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SupportService.class, BananaWebClient.class, StrawberryWebClient.class })
@TestPropertySource(properties = { "banana.api.url=https://fakebanky.herokuapp.com/fruit/banana", "strawberry.api.url=https://fakebanky.herokuapp.com/fruit/strawberry"})
class SupportServiceTest {
    @Autowired
    private SupportService supportService;

    @Test
    public void getMyAggregatedHubTest(){
        MyAggregatedHubResponse myAggregatedHubResponse = supportService.getMyAggregatedHub(new MyAggregatedHubRequest());
        assertFalse("wrong size", myAggregatedHubResponse.getMyAggregatedHubResponseItems().isEmpty());
    }

}