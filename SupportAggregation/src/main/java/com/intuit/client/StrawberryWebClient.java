package com.intuit.client;

import com.intuit.data.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * An strawberry web client.
 */
@Service
public class StrawberryWebClient {

    @Autowired
    private Environment env;

    private WebClient webClient;

    @PostConstruct
    private void postConstruct() {
        String strawberryApiUrl = env.getProperty("strawberry.api.url");
        webClient = WebClient.create(strawberryApiUrl);
    }

    /**
     * Retrieves a list of Case objects.
     * @return A list of Case objects.
     */
    public List<Case> getCases(){
        // Send the request and wait for the response to arrive.
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Case>>(){})
                .block();
    }
}
