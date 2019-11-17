package com.database.sort;

import com.database.interfaces.entities.IPerson;

import java.util.Comparator;

public class NameComparator implements Comparator<IPerson> {



    @Override
    public int compare(IPerson o1, IPerson o2) {
        if (o1.getLastName().compareTo(o2.getLastName()) > 0) {
            return 1;
        } else if (o1.getLastName().compareTo(o2.getLastName()) == 0) {
            if (o1.getFirstName().compareTo(o2.getFirstName())> 0) {
                return 0;
            }
        }
        return 0;
    }
}
