package com.database.sort;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import ru.vsu.lab.repository.IRepository;

import java.util.Comparator;

/**
 * сортировка вставками.
 */
public class InsertSorter<T> implements ISorted<T> {

    private static final Logger LOG = LoggerFactory.getLogger(InsertSorter.class);
    /**
     * метод для сортировки вставками.
     * @param repository база данных пользователя.
     * @param comparator объект компаратор.
     */
    @Override
    public void sort(final IRepository<T> repository,
                     final Comparator<T> comparator) {
        LOG.debug("[sort: {}, {} ]", repository, comparator);
        int personLength = 0;
        for (T iPerson : repository.toList()) {
            if (iPerson != null) {
                ++personLength;
            }
        }
        for (int i = 1; i < personLength; i++) {
            T current = repository.get(i);
            int j = i - 1;
            while(j >= 0 && comparator.compare(repository.get(j), current) > 0) {
                repository.set(j + 1, repository.get(j));
                j--;
            }
            repository.set(j + 1, current);
        }
    }
}
