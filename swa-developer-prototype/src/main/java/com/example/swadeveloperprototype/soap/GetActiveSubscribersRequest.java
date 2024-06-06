package com.example.swadeveloperprototype.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "getActiveSubscribersRequest", namespace = "http://example.com/subscriber")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetActiveSubscribersRequest {
    // Boş bir sınıf olabilir çünkü istekte herhangi bir alan belirtilmemiş
}
