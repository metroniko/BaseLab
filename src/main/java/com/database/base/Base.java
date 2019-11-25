package com.database.base;
import com.database.sort.*;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import java.util.*;
import java.util.function.Predicate;

/**
 * this class describes the database.
 */

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
            T[] newPersonBase = (T[]) new Object[
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
            Person tempPerson = (Person) factory.createPerson();
            tempPerson.setValues(((Person)person).getFirstName(),
                    ((Person)person).getLastName(),
                    ((Person)person).getGender(),
                    ((Person)person).getBirthdate(),
                    ((Person)person).getDivision(),
                    ((Person)person).getSalary(),
                    ((Person)person).getId()
            );

            personBase[personLenght] = (T) tempPerson;
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
        return (T)personBase[index];
    }

    /**
     * удаляет пользователя по индексу.
     * @param index индекс, по которому мужно удалить пользователя.
     * @return возвращает удалённого пользователя.
     */
    @Override
    public T delete(int index) {
        T tempPerson = (T) personBase[index];
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
            T[] newPersonBase = (T[]) new IPerson[personBase.length
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

        T[] returnedBase = (T[]) new Person[personLenght];
        System.arraycopy(personBase,
                0,
                returnedBase,
                0,
                personLenght);
        return  Arrays.asList(returnedBase);
    }
    /**
     * метод, который сортирует массив во определённому
     * методу.
     * @param comparator содержит в себе метод, по которому
     *                   сортируется массив.
     */
    @Override
    public void sortBy(final Comparator comparator) {
        BubbleSorter.sort((IPerson[]) this.personBase, comparator);
    }

    @Override
    public IRepository searchBy(final Predicate predicate) {
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
    private Optional<IPerson> getPerson(final T requiredPerson) {
        for (int i = 0; i < personLenght; i++) {
            if (personBase[i].equals(requiredPerson)) {
                return (Optional<IPerson>) Optional.of(personBase[i]);
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
    final void bubbleSortedByAge() { BubbleSorter.sort((IPerson[]) personBase,
            new AgeComparator()); }
    final void bubbleSortByFIO() { BubbleSorter.sort((IPerson[]) personBase,
            new NameComparator()); }
    final void insertSorterByAge() { InsertSorter.sort((IPerson[]) personBase,
            new AgeComparator()); }
    final void insertSorterByFio() {
        InsertSorter.sort((IPerson[]) personBase,
            new NameComparator());
    }

    /**
     * возвращает массив данных без null элементов.
     * @return массив данных без null элементов.
     */
    final T[] getAllDatabase() {

        T[] returnedBase = (T[]) new IPerson[personLenght];
        System.arraycopy(personBase,
                0,
                returnedBase,
                0,
                personLenght);
        return returnedBase;
    }


}
