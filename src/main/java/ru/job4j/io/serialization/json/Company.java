package ru.job4j.io.serialization.json;

import java.util.*;

public class Company {
    private final boolean active;
    private final int numberOfBranches;
    private final Contact contact;
    private final String[] fieldsOfActivity;

    public Company(boolean active, int numberOfBranches, Contact contact,
                   String[] fieldsOfActivity) {
        this.active = active;
        this.numberOfBranches = numberOfBranches;
        this.contact = contact;
        this.fieldsOfActivity = fieldsOfActivity;
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
