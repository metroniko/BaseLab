package com.database.base;

import com.database.sort.BubbleSorter;
import com.database.sort.ISorted;
import reflection.Injector;
import reflection.InjectorExeption;
import reflection.LabInject;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * класс репозиторий.
 * @param <T> параметр пользователя.
 */
@SuppressWarnings("unchecked")

public class Base<T> implements IRepository<T> {
    /**
     * объект фабрики.
     */
    private final Factory factory = new Factory();
    /**
     * массив объектов.
     */
    private T[] personBase = (T[]) new Object[10];

    /**.
     * number of items in the database.
     */
    private int personLenght = 0;

    /**
     * метод добавления пользователя в
     * массив пользователей.
     * @param person объект пользователя.
     */
    @Override
    final public void add(final T person) {
        if (personBase.length == personLenght) {
            T[] newPersonBase = (T[]) new IPerson[
                    personBase.length
                    + (int)Math.ceil(1.5 * personBase.length)
                    ];
            System.arraycopy(personBase,
                    0,
                    newPersonBase,
                    0,
                    personBase.length);
            personBase = newPersonBase;
        }
        if (personLenght == 0 || !getPerson(person).isPresent()) {
            T tempPerson = (T) factory.createPerson();
            ((Person)tempPerson).setValues(((Person)person).getFirstName(),
                    ((Person)person).getLastName(),
                    ((Person)person).getGender(),
                    ((Person)person).getBirthdate(),
                    ((Person)person).getDivision(),
                    ((Person)person).getSalary(),
                    ((Person)person).getId()
            );

            personBase[personLenght] = tempPerson;
            personLenght++;
        } else {
            System.out.println("Такой пользователь существует в базе");
        }
    }



    /**
     * по индексу возвращает пользователя из базы данных.
     * @param index индекс, под которым нкжно возвратить.
     * @return искомый пользователь.
     */
    @Override
    public T get(final int index) {
        return personBase[index];
    }

    /**
     * удаляет пользователя по индексу.
     * @param index индекс, по которому мужно удалить пользователя.
     * @return возвращает удалённого пользователя.
     */
    @Override
    public T delete(int index) {
        T tempPerson = personBase[index];
        for (int j = index; j < personLenght - 1; j++) {
            personBase[j] =  personBase[++j];
            j--;
        }
        personBase[personLenght - 1] = null;
        personLenght = personLenght - 1;
        return tempPerson;

    }




    /**
     * на задаваемый индекс вставляет нового пользователя.
     * @param index индекс, под которум будет вставлен
     *              пользователь.
     * @param person объект пользователя которого необходимо
     *               вставить.
     * @return вставленный пользователь.
     */
    @Override
    public T set(int index, T person) {
        personBase[index] = person;
        return personBase[index];
    }

    /**
     * метод добавления нового пользователя в базу данных.
     * @param index индекс под которым нужно добавить
     *              пользователя.
     * @param person объект пользователя, который необходимо
     *               добавить.
     */
    @Override
    public void add(int index, T person) {
        if (personBase.length == personLenght) {
            T[] newPersonBase = (T[]) new Person[personBase.length
                    + (int)Math.ceil(1.5 * personBase.length)];

            System.arraycopy(personBase,
                    0,
                    newPersonBase,
                    0,
                    personBase.length);
            personBase = newPersonBase;
        }
        //            j++;// возможна ошибка
        if (personLenght + 1 - index >= 0)
            System.arraycopy(personBase,
                    index,
                    personBase,
                    index + 1,
                    personLenght + 1 - index);
        personBase[index] = person;
        personLenght++;
    }

    /**
     * преобразует массив в список List.
     * @return новый список.
     */
    @Override
    public List<T> toList() {
        T[] returnedBase = (T[]) new IPerson[personLenght];
        System.arraycopy(personBase,
                0,
                returnedBase,
                0,
                personLenght);
        return Arrays.asList(returnedBase);
    }

    /**
     * объект для сортировки репозитория.
     */
    @LabInject
    private ISorted<T> sorter;

    /**
     * метод, который сортирует массив во определённому
     * методу.
     * @param comparator содержит в себе метод, по которому
     *                   сортируется массив.
     */
    @Override
    public void sortBy(final Comparator comparator) {
        try {
            Injector.inject(this);
        } catch ( InjectorExeption e) {
            sorter = new BubbleSorter<>();
            e.printStackTrace();
        }
        sorter.sort(this, comparator );
    }

    /**
     * поиск в репозитории с использованием  предиката.
     * @param predicate предикат в котором есть условие поиска.
     * @return возвращает репозиторий.
     */
    @Override
    public IRepository<T> searchBy(final Predicate predicate) {
        for (T p:
                personBase) {
            if (predicate.test(p)) {
                return this;
            }
        }
        return null;
    }


    /**
     *  a method that retrieves an item from a database.
     * @param requiredPerson the object to be removed from the database.
     * @return if the object is present, it returns it.
     */
    private Optional<T> getPerson(final T requiredPerson) {
        for (int i = 0; i < personLenght; i++) {
            if (personBase[i].equals(requiredPerson)) {
                return Optional.of(personBase[i]);
            }
        }
        return Optional.empty();
    }


    /**
     * the method that displays the database.
     */
    final void showAllDatabase() {
        for (T item: personBase) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }
    /**
     * возвращает массив данных без null элементов.
     * @return массив данных без null элементов.
     */
    final IPerson[] getAllDatabase() {

        IPerson[] returnedBase =  new Person[personLenght];
        System.arraycopy(personBase,
                0,
                returnedBase,
                0,
                personLenght);
        return returnedBase;
    }


}
