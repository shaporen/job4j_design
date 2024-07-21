package ru.job4j.io.serialization.json;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "contact")
public class Contact {
    @XmlAttribute
    private String phone;

    public Contact() { }

    public Contact(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
