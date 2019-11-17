package com.database.sort;

import com.database.interfaces.entities.IPerson;

import java.util.Comparator;

public class BubbleSorter {
    public static void sort(IPerson[] personBase, Comparator<IPerson> comparator) {
        int personLenght = 0;
        for (IPerson iPerson : personBase) {
            if (iPerson != null) {
                personLenght++;
            }
        }
        for (int i = personLenght - 1; i >= 1; i--){  //Внешний цикл
            for (int j = 0; j < i; j++){       //Внутренний цикл
                if((comparator.compare(personBase[j], personBase[j +1])) > 0)   {
                    IPerson tempPerson = personBase[j];
                    personBase[j] = personBase[j + 1];
                    personBase[j + 1] = tempPerson;
                }            //Если порядок элементов нарушен

            }
        }
    }
}
