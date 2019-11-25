package com.database.sort;



import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * компаратор по годам
 */
public class AgeComparator<T> implements Comparator<T> {
    /**
     * @param o1 1 сравниваемый параметр.
     * @param o2 2 сравниваемый параметр.
     * @return 0 или 1
     */
    @Override
    public int compare(final T o1, final T o2) {
        if (((IPerson)o1).getBirthdate().isBefore(((IPerson)o2).getBirthdate())) {
            return 1;
        }
        return 0;
    }
}
