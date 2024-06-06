package com.example.swadeveloperprototype.soap;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id"
})
@XmlRootElement(name = "GetSubscriberByIdRequest", namespace = "http://example.com/subscriber")
public class GetSubscriberByIdRequest {

    @XmlElement(required = true, namespace = "http://example.com/subscriber")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
