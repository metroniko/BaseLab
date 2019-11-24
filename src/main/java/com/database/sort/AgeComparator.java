package com.database.sort;



import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * компаратор по годам
 */
public class AgeComparator implements Comparator<IPerson> {
    /**
     * @param o1 1 сравниваемый параметр.
     * @param o2 2 сравниваемый параметр.
     * @return 0 или 1
     */
    @Override
    public int compare(final IPerson o1, final IPerson o2) {
        if (o1.getBirthdate().isBefore(o2.getBirthdate())) {
            return 1;
        }
        return 0;
    }
}
