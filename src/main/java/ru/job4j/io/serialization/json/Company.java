package ru.job4j.io.serialization.json;

import jakarta.xml.bind.annotation.*;

import java.util.*;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
    @XmlAttribute
    private boolean active;
    @XmlAttribute
    private int numberOfBranches;
    private Contact contact;
    @XmlElementWrapper(name = "fieldsOfActivity")
    @XmlElement(name = "fieldOfActivity")
    private String[] fieldsOfActivity;

    public Company() { }

    public Company(boolean active, int numberOfBranches, Contact contact,
                   String[] fieldsOfActivity) {
        this.active = active;
        this.numberOfBranches = numberOfBranches;
        this.contact = contact;
        this.fieldsOfActivity = fieldsOfActivity;
    }

    public boolean isActive() {
        return active;
    }

    public int getNumberOfBranches() {
        return numberOfBranches;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getFieldsOfActivity() {
        return fieldsOfActivity;
    }

    @Override
    public String toString() {
        return "Company{"
                + "active=" + active
                + ", numberOfBranches=" + numberOfBranches
                + ", contact=" + contact
                + ", fieldsOfActivity=" + Arrays.toString(fieldsOfActivity)
                + '}';
    }
}
