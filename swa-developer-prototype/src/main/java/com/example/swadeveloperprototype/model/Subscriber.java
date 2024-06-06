package com.example.swadeveloperprototype.model;
import jakarta.persistence.*;
import jakarta.persistence.Id;


@Entity
@Table(name = "subscriberdb")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "msisdn")
    private String msisdn;
    @Column(name = "status")
    private String status;

    // Constructor without parameters
    public Subscriber() {
    }

    // Constructor with parameters
    public Subscriber(Long id, String name, String msisdn, String status) {
        this.id = id;
        this.name = name;
        this.msisdn = msisdn;
        this.status = status;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for msisdn
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // To string method to display object details
    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}