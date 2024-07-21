package ru.job4j.io.serialization.json;

import org.json.*;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Company company = new Company(true, 5, new Contact("60-59-72"),
                new String[] {"Trader", "Agroholding"});

        JSONObject jsoncontact = new JSONObject("{\"phone\":\"60-59-72\"}");
        List<String> list = new ArrayList<>();
        list.add("Trader");
        list.add("Agroholding");
        JSONArray jsonFields = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("active", company.isActive());
        jsonObject.put("numberOfBranches", company.getNumberOfBranches());
        jsonObject.put("contact", jsoncontact);
        jsonObject.put("fieldsOfActivity", jsonFields);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(company));
    }
}
