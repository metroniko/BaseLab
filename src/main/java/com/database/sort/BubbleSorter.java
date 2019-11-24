package com.database.sort;


import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * пузырьковая сортировка.
 */
public class BubbleSorter {
    /**
     * метод сортировки пузырьком.
     * @param personBase базаданных, которую необходимо
     *                   отсортировать.
     * @param comparator компаратоп.
     */
    public static void sort(final IPerson[] personBase,
                            final Comparator<IPerson> comparator) {
        int personLenght = 0;
        for (IPerson iPerson : personBase) {
            if (iPerson != null) {
                personLenght++;
            }
        }
        for (int i = personLenght - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if ((comparator.compare(personBase[j],
                        personBase[j + 1])) > 0) {
                    IPerson tempPerson = personBase[j];
                    personBase[j] = personBase[j + 1];
                    personBase[j + 1] = tempPerson;
                }

            }
        }
    }
}
