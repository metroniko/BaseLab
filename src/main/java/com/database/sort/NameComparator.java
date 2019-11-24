package com.database.sort;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * компаратор по имени.
 */
public class NameComparator implements Comparator<IPerson> {
    /**
     *
     * @param o1 1 сравниваемый параметр.
     * @param o2 2 сравниваемый параметр.
     * @return 0 или 1
     */
    @Override
    public int compare(final IPerson o1, final IPerson o2) {
        if (o1.getLastName().compareTo(o2.getLastName()) > 0) {
            return 1;
        } else if (o1.getLastName().compareTo(o2.getLastName()) == 0) {
            if (o1.getFirstName().compareTo(o2.getFirstName()) > 0) {
                return 0;
            }
        }
        return 0;
    }
}
