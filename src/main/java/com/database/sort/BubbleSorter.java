package com.database.sort;


import ru.vsu.lab.repository.IRepository;

import java.util.Comparator;

/**
 * пузырьковая сортировка.
 */
public class BubbleSorter<T> implements ISorted<T> {
    /**
     * метод сортировки пузырьком.
     * @param repository базаданных, которую необходимо
     *                   отсортировать.
     * @param comparator компаратоп.
     */
    @Override
    public void sort(final IRepository<T> repository,
                     final Comparator<T> comparator) {
        int personLenght = 0;
        for (Object iPerson : repository.toList()) {
            if (iPerson != null) {
                personLenght++;
            }
        }
        for (int i = personLenght - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if ((comparator.compare(repository.get(j),
                        repository.get(j + 1))) > 0) {
                    T tempPerson = repository.get(j);
                    repository.set(j, repository.get(j + 1));
                    repository.set(j + 1, tempPerson);
                }

            }
        }
    }
}
