package com.database.sort;

import com.database.interfaces.entities.IPerson;

import java.util.Comparator;

public class InsertSorter {
    public static void sort(IPerson[] personBase, Comparator<IPerson> comparator) {
        int personLenght = 0;
        for (IPerson iPerson : personBase) {
            if (iPerson != null) {
                ++personLenght;
            }
        }
        for (int i = 1; i < personLenght; i++) {
            IPerson current = personBase[i];
            int j = i - 1;
            while(j >= 0 && comparator.compare(personBase[j], current) > 0) {
                personBase[j+1] = personBase[j];
                j--;
            }
            personBase[j+1] = current;
        }
    }
}
