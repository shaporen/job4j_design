package ru.job4j.io.serialization.xml;

import ru.job4j.io.serialization.json.Contact;

import java.util.*;

public class Company {
    private final boolean active;
    private final String name;
    private final int numberOfBranches;
    private final Contact contact;
    private final String[] fieldsOfActivity;

    public Company(boolean active, String name, int numberOfBranches, Contact contact,
                   String[] fieldsOfActivity) {
        this.active = active;
        this.name = name;
        this.numberOfBranches = numberOfBranches;
        this.contact = contact;
        this.fieldsOfActivity = fieldsOfActivity;
    }

    @Override
    public String toString() {
        return "Company{"
                + "active=" + active
                + ", name='" + name + '\''
                + ", numberOfBranches=" + numberOfBranches
                + ", contact=" + contact
                + ", fieldsOfActivity=" + Arrays.toString(fieldsOfActivity)
                + '}';
    }
}
