package com.kblyumkin.userServices.services;

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name="User", propOrder = {
        "id",
        "firstName",
        "lastName",
        "status",
        "creationTime"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private Status status;
    private long creationTime;

    public User() {
    }

    public User(String id, String firstName, String lastName, Status status, long creationTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.creationTime = creationTime;
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

    @SpaceIndex
    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }

    public enum Status {NEW, UPDATED, REJECT};
}
