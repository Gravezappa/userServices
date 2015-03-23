package com.kblyumkin.userServices.services;

import com.gigaspaces.annotation.pojo.SpaceId;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name="User", propOrder = {
        "id",
        "firstName",
        "lastName",
        "status",
        "timeStamp"
})
@XmlAccessorType(XmlAccessType.FIELD)
/*@XmlRootElement(name="User")*/
public class User implements Serializable {
    /*@XmlElement(required = true)*/
    private String id;
    /*@XmlElement(required = true)*/
    private String firstName;
    /*@XmlElement(required = true)*/
    private String lastName;
    /*@XmlElement(required = true)*/
    private Status status;
    /*@XmlElement(required = true)*/
    private String timeStamp;

    public User() {
    }

    public User(String id, String firstName, String lastName, Status status, String timeStamp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    @SpaceId(autoGenerate = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    public enum Status {NEW, UPDATED, REJECT};
}
