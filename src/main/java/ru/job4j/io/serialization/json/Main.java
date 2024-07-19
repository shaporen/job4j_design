package ru.job4j.io.serialization.json;

import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        final Company company = new Company(true, 5, new Contact("60-59-72"),
                new String[] {"Trader", "Agroholding", "Elevator"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(company));

        final String companyJson =
                "{"
                        + "\"active\":true,"
                        + "\"numberOfBranches\":5,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"60-59-72\""
                        + "},"
                        + "\"fieldsOfActivity\":"
                        + "[\"Trader\",\"Agroholding\",\"Elevator\"]"
                        + "}";

        final Company companyMod = gson.fromJson(companyJson, Company.class);
        System.out.println(companyMod);
    }
}
