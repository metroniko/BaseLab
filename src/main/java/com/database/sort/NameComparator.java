package com.database.sort;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * компаратор по имени.
 */
public class NameComparator<T> implements Comparator<T> {
    /**
     *
     * @param o1 1 сравниваемый параметр.
     * @param o2 2 сравниваемый параметр.
     * @return 0 или 1
     */
    @Override
    public int compare(final T o1, final T o2) {
        if (((IPerson) o1).getLastName().compareTo(((IPerson) o2).getLastName()) > 0) {
            return 1;
        } else if (((IPerson) o1).getLastName().compareTo(((IPerson) o2).getLastName()) == 0) {
            if (((IPerson) o1).getFirstName().compareTo(((IPerson) o2).getFirstName()) > 0) {
                return 0;
            }
        }
        return 0;
    }
}
