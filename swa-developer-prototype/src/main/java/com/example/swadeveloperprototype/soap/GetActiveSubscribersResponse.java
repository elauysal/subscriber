package com.example.swadeveloperprototype.soap;

import com.example.swadeveloperprototype.model.Subscriber;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "getActiveSubscribersResponse", namespace = "http://example.com/subscriber")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetActiveSubscribersResponse {

    @XmlElement(name = "subscriber")
    private List<Subscriber> subscribers;

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
