package com.database;

import com.database.interfaces.entities.IPerson;
import com.database.interfaces.repository.IRepository;
import com.database.sort.*;

import java.util.*;
import java.util.function.Predicate;

/**
 * this class describes the database.
 */
public class Base implements IRepository  {


    private IPerson[] personBase = new Person[10];

    /**.
     * number of items in the database.
     */
    private int personLenght = 0;

    /**
     * метод добавления пользователя в массив пользователей
     * @param person объект пользователя
     */
    @Override
    public void add(IPerson person) {
        if (personBase.length == personLenght) {
            IPerson[] newPersonBase = new IPerson[personBase.length + (int)Math.ceil(1.5 * personBase.length)];

            System.arraycopy(personBase,
                    0,
                    newPersonBase,
                    0,
                    personBase.length);
            personBase = newPersonBase;
        }
        if (personLenght == 0 || !getPerson(person).isPresent()) {
            Person tempPerson = new Person();
            tempPerson.setValues(person.getFirstName(),
                    person.getLastName(),
                    person.getGender(),
                    person.getBirthdate(),
                    person.getDivision(),
                    person.getSalary(),
                    person.getId()
);

            personBase[personLenght] = tempPerson;
            personLenght++;
        } else {
            System.out.println("Такой пользователь существует в базе");
        }
    }


    /**
     * по индексу возвращает пользователя из базы данных
     * @param index индекс, под которым нкжно возвратить
     * @return искомый пользователь
     */
    @Override
    public IPerson get(int index) {
        return personBase[index];
    }

    /**
     * удаляет пользователя по индексу
     * @param index индекс, по которому мужно удалить пользователя
     * @return возвращает удалённого пользователя
     */
    @Override
    public IPerson delete(int index) {
        IPerson tempPerson = personBase[index];
        for (int j = index; j < personLenght - 1; j++) {
            personBase[j] =  personBase[++j];
            j--;
        }
        personBase[personLenght - 1] = null;
        personLenght = personLenght - 1;
        return tempPerson;

    }

    /**
     * на задаваемый индекс вставляет нового пользователя
     * @param index индекс, под которум будет вставлен пользователь
     * @param person объект пользователя которого необходимо вставить
     * @return вставленный пользователь
     */
    @Override
    public IPerson set(int index, IPerson person) {
        personBase[index] = person;
        return personBase[index];
    }

    /**
     * метод добавления нового пользователя в базу данных
     * @param index индекс под которым нужно добавить пользователя
     * @param person объект пользователя, который необходимо добавить
     */

    @Override
    public void add(int index, IPerson person) {
        if (personBase.length == personLenght) {
            IPerson[] newPersonBase = new Person[personBase.length + (int)Math.ceil(1.5 * personBase.length)];

            System.arraycopy(personBase,
                    0,
                    newPersonBase,
                    0,
                    personBase.length);
            personBase = newPersonBase;
        }
        //            j++;// возможна ошибка
        if (personLenght + 1 - index >= 0)
            System.arraycopy(personBase, index, personBase, index + 1, personLenght + 1 - index);
        personBase[index] = person;
        personLenght++;
    }

    /**
     * преобразует массив в список List
     * @return новый список
     */
    @Override
    public List<IPerson> toList() {
        IPerson[] returnedBase = new Person[personLenght];
        System.arraycopy(personBase,
                0,
                returnedBase,
                0,
                personLenght);
        return Arrays.asList(returnedBase);
    }

    /**
     * метод, который сортирует массив во определённому методу
     * @param comparator содержит в себе метод, по которому сортируется массив
     */
    @Override
    public void sortBy(Comparator<IPerson> comparator) {
        BubbleSorter.sort(this.personBase, comparator);
    }

    /**
     * метод поиска по определённому критерию
     * @param condition критерий ввиде придеката
     * @return возбращает объект базы данных
     */
    @Override
    public IRepository searchBy(Predicate<IPerson> condition) {
        for (IPerson p:
             personBase) {
            if (condition.test(p)) {
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
    private Optional<IPerson> getPerson(final IPerson requiredPerson) {
        for (int i = 0; i < personLenght; i++) {
            if (personBase[i].equals(requiredPerson)) {
                return Optional.of(personBase[i]);
            }
        }
        return Optional.empty();
    }

// --Commented out by Inspection START (16.11.2019 20:17):
//    /**
//     * a method that retrieves an item from a database by user name.
//     * @param requiredFisrtName name to be found.
//     * @param requiredlastName name to be found.
//     * @return if the object is present, it returns it.
//     */
//    public final Optional<IPerson> getPersonbyFIO(final String requiredFisrtName, final String requiredlastName) {
//        for (int i = 0; i < personLenght; i++) {
//            if (requiredFisrtName.equals(personBase[i].getFirstName()) && requiredlastName.equals(personBase[i].getLastName())) {
//                return Optional.of(personBase[i]);
//            }
//        }
//        return Optional.empty();
//    }
// --Commented out by Inspection STOP (16.11.2019 20:17)


    /**
     * the method that displays the database.
     */
    public final void showAllDatabase() {
        for (IPerson item: personBase) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }
    void bubbleSortedByAge() { BubbleSorter.sort(personBase, new AgeComparator()); }
    void bubbleSortByFIO() { BubbleSorter.sort(personBase, new NameComparator()); }
    void insertSorterByAge() { InsertSorter.sort(personBase,new AgeComparator()); }
    void insertSorterByFio() {
        InsertSorter.sort(personBase,new NameComparator());
    }

    /**
     * возвращает массив данных без null элементов
     * @return массив данных без null элементов
     */
    IPerson[] getAllDatabase() {

        IPerson[] returnedBase = new Person[personLenght];
        System.arraycopy(personBase,
                0,
                returnedBase,
                0,
                personLenght);
        return returnedBase;
    }


}
