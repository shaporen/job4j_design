package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        HashMap<Integer, User> users = new HashMap<>();

        for (User prev : previous) {
            users.put(prev.getId(), prev);
        }

        for (User cur : current) {
            if (!users.containsKey(cur.getId())) {
                added++;
            }
            if (users.containsKey(cur.getId()) && !users.containsValue(cur)) {
                changed++;
            }
            users.remove(cur.getId());
        }
        deleted = users.size();
        return new Info(added, changed, deleted);
    }
}
