package com.database.sort;

import ru.vsu.lab.repository.IRepository;

import java.util.Comparator;

/**
 * интерфейс сортировки.
 * @param <T> параметр типизации.
 */
public interface ISorted<T> {
    void sort(final IRepository<T> repository,final Comparator<T> comparator);
}
