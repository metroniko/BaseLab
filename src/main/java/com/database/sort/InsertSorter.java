package com.database.sort;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * сортировка вставками.
 */
public class InsertSorter {
    /**
     * метод для сортировки вставками.
     * @param personBase база данных пользователя.
     * @param comparator объект компаратор.
     */
    public static void sort(final IPerson[] personBase, final Comparator<IPerson> comparator) {
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
                personBase[j + 1] = personBase[j];
                j--;
            }
            personBase[j + 1] = current;
        }
    }
}
