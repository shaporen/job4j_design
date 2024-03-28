package ru.job4j.iterator;

import java.util.*;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (source.hasNext()) {
            nodes.get(index++).add(source.next());
            if (!nodes.isEmpty() && nodes.size() == index) {
                index = 0;
            }
        }
    }
}
