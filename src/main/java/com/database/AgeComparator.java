package com.database;

public class AgeComparator implements Comprble {
    @Override
    public boolean compare(Person p1, Person p2) {
        if (p1.getYearOfBirthday() > p2.getYearOfBirthday()) {
            return true;
        } else return p1.getYearOfBirthday() == p2.getYearOfBirthday() && p1.getMonthOfBirth() > p2.getMonthOfBirth();
    }
}
