package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(Calendar birthday, int children, String name) {
        this.birthday = birthday;
        this.children = children;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> users = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        User user1 = new User(birthday, 21, "Alex");
        int hashcode1 = user1.hashCode();
        int hash1 = hashcode1 ^ (hashcode1 >>> 16);
        int bucket1 = hash1 & 15;
        User user2 = new User(birthday, 21, "Alex");
        int hashcode2 = user2.hashCode();
        int hash2 = hashcode2 ^ (hashcode1 >>> 16);
        int bucket2 = hash2 & 15;
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.printf(hashcode1 + " " + hash1 + " " + bucket1);
        System.out.println();
        System.out.printf(hashcode2 + " " + hash2 + " " + bucket2);
        System.out.println();
        System.out.println(users);
    }
}
