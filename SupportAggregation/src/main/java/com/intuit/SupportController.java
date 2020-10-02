package com.intuit;

import com.intuit.comm.MyAggregatedHubRequest;
import com.intuit.comm.MyAggregatedHubResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A support rest controller.
 * Accepts requests coming from clients.
 */
@RestController
public class SupportController {

    @Autowired
    private SupportService supportService;

    /**
     * Retrieves the relevant cases.
     * @return A MyAggregatedHubResponse object.
     */
    @GetMapping("/myAggregatedHub")
    public MyAggregatedHubResponse getMyAggregatedHub(@RequestBody MyAggregatedHubRequest myAggregatedHubRequest){
        return supportService.getMyAggregatedHub(myAggregatedHubRequest);
    }
}

