package com.intuit.client;

import com.intuit.data.Case;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { StrawberryWebClient.class })
@TestPropertySource(properties = { "strawberry.api.url=https://fakebanky.herokuapp.com/fruit/strawberry"})
class StrawberryWebClientTest {

    @Autowired
    private StrawberryWebClient strawberryWebClient;

    @Test
    public void getCasesTest(){
        List<Case> cases = strawberryWebClient.getCases();
        assertFalse("wrong size", cases.isEmpty());
    }
}