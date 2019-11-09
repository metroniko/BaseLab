package com.database;

import java.util.Optional;

/**
 * this class describes the database.
 */
public class Base {

    /**.
     * a field that stores database items.
     */
    private Person[] personBase = new Person[10];

    /**.
     * number of items in the database.
     */
    private int personLenght = 0;

    /**
     *  a method that retrieves an item from a database.
     * @param requiredPerson the object to be removed from the database.
     * @return if the object is present, it returns it.
     */
    private Optional<Person> getPerson(final Person requiredPerson) {
        for (int i = 0; i < personLenght; i++) {
            if (personBase[i].equals(requiredPerson)) {
                return Optional.of(personBase[i]);
            }
        }
        return Optional.empty();
    }

    /**
     * a method that retrieves an item from a database by user name.
     * @param requiredName name to be found.
     * @return if the object is present, it returns it.
     */
    public final Optional<Person> getPersonbyFIO(final String requiredName) {
        for (int i = 0; i < personLenght; i++) {
            if (requiredName.equals(personBase[i].getFio())) {
                return Optional.of(personBase[i]);
            }
        }
        return Optional.empty();
    }

    public final Optional<Person> getPersonByBirthday (final int year, final int month, final int day) {
        for (int i = 0; i < personLenght; i++) {
            if (year == personBase[i].getYearOfBirthday() && month == personBase[i].getMonthOfBirth() && day == personBase[i].getBirthday()) {
                return Optional.of(personBase[i]);
            }
        }
        return Optional.empty();
    }

    /**
     * a method that removes a user by name.
     * @param deletedName the field name of the object to be deleted.
     */
    public final void deletePersonbyFIO(final String deletedName) {
        for (int i = 0; i < personLenght; i++) {
            if (deletedName.equals(personBase[i].getFio())) {
                for (int j = i; j < personLenght - 1; j++) {
                    personBase[j] =  personBase[++j];
                    j--;
                }
                personBase[personLenght - 1] = null;
                personLenght--;
                return;
            }
        }
    }


    /**
     * the method implements adding items to the database.
     * @param person object to add.
     */
    public final void addNewPerson(final Person person) {

        if (personBase.length == personLenght) {
            Person[] newPersonBase = new Person[personBase.length + 5];

            System.arraycopy(personBase,
                    0,
                    newPersonBase,
                    0,
                    personBase.length);
            personBase = newPersonBase;
        }
        if (personLenght == 0 || !getPerson(person).isPresent()) {
            Person tempPerson = new Person();
            tempPerson.setValues(person.getFio(),
                    person.getGender(),
                    person.getBirthday(),
                    person.getMonthOfBirth(),
                    person.getYearOfBirthday());

            personBase[personLenght] = tempPerson;
            personLenght++;
        } else {
            System.out.println("Такой пользователь существует в базе");
        }

    }

    /**
     * the method that displays the database.
     */
    public final void showAllDatabase() {
        for (Person item: personBase) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }
    public void bubbleSortedByAge() {
        bubbleSorter(new AgeComparator());
    }
    public void bubbleSortByFIO() {
        bubbleSorter(new FioComparator());
    }

    private void bubbleSorter(Comprble comparator) {     //МЕТОД ПУЗЫРЬКОВОЙ СОРТИРОВКИ
        for (int i = personLenght - 1; i >= 1; i--){  //Внешний цикл
            for (int j = 0; j < i; j++){       //Внутренний цикл
                if(comparator.compare(personBase[j], personBase[j +1]))   {
                    Person tempPerson = personBase[j];
                    personBase[j] = personBase[j + 1];
                    personBase[j + 1] = tempPerson;
                }            //Если порядок элементов нарушен

            }
        }
    }
    public void insertSorterByAge() {
        insertSorter(new AgeComparator());
    }
    public void insertSorterByFio() {
        insertSorter(new FioComparator());
    }
    private void insertSorter(Comprble comparator) {
        for (int i = 1; i < personLenght; i++) {
            Person current = personBase[i];
            int j = i - 1;
            while(j >= 0 && comparator.compare(personBase[j], current)) {
                personBase[j+1] = personBase[j];
                j--;
            }
            personBase[j+1] = current;
        }
    }

    public Person[] getAllDatabase() {

        Person[] returnedBase = new Person[personLenght];
        System.arraycopy(personBase,
                0,
                returnedBase,
                0,
                personLenght);
        return returnedBase;
    }


}
