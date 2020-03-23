package com.database.sort;


import com.database.base.Base;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import ru.vsu.lab.repository.IRepository;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Comparator;

/**
 * пузырьковая сортировка.
 */
@XmlRootElement
public class BubbleSorter<T> implements ISorted<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BubbleSorter.class);

    /**
     * метод сортировки пузырьком.
     * @param repository базаданных, которую необходимо
     *                   отсортировать.
     * @param comparator компаратоп.
     */
    @Override
    public void sort(final IRepository<T> repository,
                     final Comparator<T> comparator) {
        LOG.debug("[sort: {}, {} ]", repository, comparator);
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
