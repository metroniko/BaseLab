package com.database.sort;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * компоратор по имени.
 * @param <T> параметр типизации.
 */
public class NameComparator<T> implements Comparator<T> {


    private static final Logger LOG = LoggerFactory.getLogger(NameComparator.class);
    /**
     *
     * @param o1 1 сравниваемый параметр.
     * @param o2 2 сравниваемый параметр.
     * @return 0 или 1
     */

    @Override
    public int compare(final T o1, final T o2) {

        LOG.debug("[compare: {}, {} ", o1, o2);
        if (((IPerson) o1).getLastName()
                .compareTo(((IPerson) o2)
                        .getLastName()) > 0) {
            LOG.debug("] return : {}", true);
            return 1;
        } else if (((IPerson) o1).getLastName()
                .compareTo(((IPerson) o2)
                        .getLastName()) == 0) {
            if (((IPerson) o1).getFirstName()
                    .compareTo(((IPerson) o2)
                            .getFirstName()) > 0) {
                LOG.debug("] return : {}", false);
                return 0;
            }
        }
        LOG.debug("] return : {}", false);
        return 0;
    }
}
