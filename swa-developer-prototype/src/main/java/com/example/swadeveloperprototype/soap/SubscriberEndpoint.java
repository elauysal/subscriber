package com.example.swadeveloperprototype.soap;

import com.example.swadeveloperprototype.cache.SubscriberCache;
import com.example.swadeveloperprototype.model.Subscriber;
import com.example.swadeveloperprototype.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class SubscriberEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/subscriber";

    @Autowired
    private DataService dataService;

    @Autowired
    private SubscriberCache subscriberCache;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllSubscribersRequest")
    @ResponsePayload
    public GetAllSubscribersResponse getAllSubscribers(@RequestPayload GetAllSubscribersRequest request) {
        GetAllSubscribersResponse response = new GetAllSubscribersResponse();
        List<Subscriber> subscribers = subscriberCache.getSubscribers();
        response.getSubscribers().addAll(subscribers);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getActiveSubscribersRequest")
    @ResponsePayload
    public GetActiveSubscribersResponse getActiveSubscribers(@RequestPayload GetActiveSubscribersRequest request) {
        GetActiveSubscribersResponse response = new GetActiveSubscribersResponse();
        List<Subscriber> activeSubscribers = dataService.loadActiveSubscribersFromDatabase();
        response.getSubscribers().addAll(activeSubscribers);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSubscriberByIdRequest")
    @ResponsePayload
    public GetSubscriberByIdResponse getSubscriberById(@RequestPayload GetSubscriberByIdRequest request) {
        GetSubscriberByIdResponse response = new GetSubscriberByIdResponse();
        Subscriber subscriber = subscriberCache.getSubscribers().stream()
                .filter(s -> s.getId().equals(request.getId()))
                .findFirst()
                .orElse(null);
        response.setSubscriber(subscriber);
        return response;
    }
}
