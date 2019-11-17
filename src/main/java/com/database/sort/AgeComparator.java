package com.database.sort;

import com.database.interfaces.entities.IPerson;

import java.util.Comparator;

public class AgeComparator implements Comparator<IPerson> {
    @Override
    public int compare(IPerson o1, IPerson o2) {
        if (o1.getBirthdate().isBefore(o2.getBirthdate())) {
            return 1;
        }
        return 0;
    }
}
