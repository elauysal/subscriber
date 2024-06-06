package com.example.swadeveloperprototype.soap;

import com.example.swadeveloperprototype.model.Subscriber;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetSubscriberByIdResponse", namespace = "http://example.com/subscriber")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSubscriberByIdResponse {

    @XmlElement(name = "subscriber")
    private Subscriber subscriber;

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
