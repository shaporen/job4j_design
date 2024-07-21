package ru.job4j.io.serialization.json;

import jakarta.xml.bind.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Company company = new Company(true, 5, new Contact("60-59-72"),
                new String[] {"Trader", "Agroholding"});

        JAXBContext context = JAXBContext.newInstance(Company.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(company, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Company result = (Company) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
