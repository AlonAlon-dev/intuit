package com.intuit;

import com.intuit.client.BananaWebClient;
import com.intuit.client.StrawberryWebClient;
import com.intuit.comm.MyAggregatedHubRequest;
import com.intuit.comm.MyAggregatedHubResponse;
import com.intuit.data.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Holds all the cases available for the server.
 */
@Service
public class SupportService {

    private static final int REFRESH_INTERVAL_MIN = 15;

    @Autowired
    private BananaWebClient bananaWebClient;

    @Autowired
    private StrawberryWebClient strawberryWebClient;

    private StampedLock refreshLock;

    private LocalDateTime lastRefreshDateTime;

    private List<Case> bananaCaseList;

    private List<Case> strawberryCaseList;

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    private void postConstruct() {
        refreshLock = new StampedLock();

        bananaCaseList = new ArrayList<>();
        strawberryCaseList = new ArrayList<>();

        // Set the timestamp to the beginning of time.
        lastRefreshDateTime = LocalDateTime.MIN;

        scheduledExecutorService.scheduleAtFixedRate(()-> {
            refresh();
        }, 0, REFRESH_INTERVAL_MIN, TimeUnit.MINUTES);
    }

    private void refresh(){
        long stamp = refreshLock.writeLock();
        try {
            // Check that enough time has elapsed from the last refresh
            LocalDateTime fifteenMinutesAgo = LocalDateTime.now().minusMinutes(REFRESH_INTERVAL_MIN);
            if (lastRefreshDateTime.isAfter(fifteenMinutesAgo)) {
                return;
            }

            // Refresh banana cases.
            bananaCaseList = bananaWebClient.getCases();

            // Refresh strawberry cases.
            strawberryCaseList = strawberryWebClient.getCases();

            // Store the time stamp.
            lastRefreshDateTime = LocalDateTime.now();
        } finally {
            refreshLock.unlock(stamp);
        }
    }

    /**
     * Gets all available banana cases
     *
     * @return an unmodifiable view of the banana cases list.
     */
    private List<Case> getBananaCases(){
        long stamp = refreshLock.readLock();
        try {
            return Collections.unmodifiableList(bananaCaseList);
        }finally {
            refreshLock.unlock(stamp);
        }
    }

    /**
     * Gets all available strawberry cases
     *
     * @return an unmodifiable view of the strawberry cases list.
     */
    private List<Case> getStrawberryCases(){
        long stamp = refreshLock.readLock();
        try {
            return Collections.unmodifiableList(strawberryCaseList);
        }finally {
            refreshLock.unlock(stamp);
        }
    }

    /**
     * Retrieves the relevant cases.
     * @return A MyAggregatedHubResponse object.
     * @param myAggregatedHubRequest The request.
     */
    public MyAggregatedHubResponse getMyAggregatedHub(MyAggregatedHubRequest myAggregatedHubRequest){
        List<MyAggregatedHubResponse.MyAggregatedHubResponseItem> myAggregatedHubResponseItemList =
                Stream.concat(getBananaCases().stream(), getStrawberryCases().stream())
                        .filter(caseObject -> Arrays.asList("BLUE", "RED", "GREEN").contains(caseObject.getProductName()))
                        .filter(caseObject -> myAggregatedHubRequest.getCustomerId() == 0 || myAggregatedHubRequest.getCustomerId() == caseObject.getCustomerId())
                        .filter(caseObject -> myAggregatedHubRequest.getProvider() == 0 || myAggregatedHubRequest.getProvider() == caseObject.getProvider())
                        .filter(caseObject -> myAggregatedHubRequest.getCreatedErrorCode() == 0 || myAggregatedHubRequest.getCreatedErrorCode() == caseObject.getCreatedErrorCode())
                        .filter(caseObject -> myAggregatedHubRequest.getStatus() == null || myAggregatedHubRequest.getStatus().equals(caseObject.getStatus()))
                        .filter(caseObject -> myAggregatedHubRequest.getProductName() == null || myAggregatedHubRequest.getProductName().equals(caseObject.getProductName()))
                        .collect(Collectors.groupingBy(caseObject -> caseObject.getCreatedErrorCode() + "_" + caseObject.getProvider()))
                        .values()
                        .stream()
                        .map(caseList -> {
                            MyAggregatedHubResponse.MyAggregatedHubResponseItem myAggregatedHubResponseItem = new MyAggregatedHubResponse.MyAggregatedHubResponseItem();
                            myAggregatedHubResponseItem.setCaseList(caseList);
                            myAggregatedHubResponseItem.setCasesNum(caseList.size());
                            myAggregatedHubResponseItem.setCreatedErrorCode(caseList.get(0).getCreatedErrorCode());
                            myAggregatedHubResponseItem.setProvider(caseList.get(0).getProvider());
                            myAggregatedHubResponseItem.setProductNameList(
                                    caseList
                                            .stream()
                                            .map(Case::getProductName)
                                            .distinct()
                                            .collect(Collectors.toList())
                            );
                            return myAggregatedHubResponseItem;
                        })
                        .collect(Collectors.toList());

        // Prepare the response.
        MyAggregatedHubResponse myAggregatedHubResponse = new MyAggregatedHubResponse();
        myAggregatedHubResponse.setMyAggregatedHubResponseItems(myAggregatedHubResponseItemList);
        return myAggregatedHubResponse;
    }
}