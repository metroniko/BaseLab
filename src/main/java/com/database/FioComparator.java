package com.database;

public class FioComparator implements Comprble {
    @Override
    public boolean compare(Person p1, Person p2) {
        for (int i = 0; i < p1.getFio().length(); i++) {

            if (p1.getFio().charAt(i) == p2.getFio().charAt(i)) {
            } else return p1.getFio().charAt(i) > p2.getFio().charAt(i);
        }
        return true;
    }
}
