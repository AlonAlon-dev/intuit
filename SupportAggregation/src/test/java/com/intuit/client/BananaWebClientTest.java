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
@ContextConfiguration(classes = { BananaWebClient.class })
@TestPropertySource(properties = { "banana.api.url=https://fakebanky.herokuapp.com/fruit/banana"})
class BananaWebClientTest {

    @Autowired
    private BananaWebClient bananaWebClient;

    @Test
    public void getCasesTest(){
        List<Case> cases = bananaWebClient.getCases();
        assertFalse("wrong size", cases.isEmpty());
    }
}