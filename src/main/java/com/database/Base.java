package com.database;


/**
 * this class contains a database
 */
public class Base {
    /**
     * a field that stores database items
     */
    private Person[] personBase = new Person[10];

    /**
     * number of items in the database
     */
    private int personLenght = 0;

    /**
     *  a method that retrieves an item from a database
     * @param requiredPerson the object to be removed from the database
     * @return if the object is present, it returns it
     */
    private Person getPerson(Person requiredPerson) {
        for (int i = 0; i < personLenght; i++) {
            if (  personBase[i].equals(requiredPerson)) {
                return personBase[i];
            }
        }
        return null;
    }

    public int getPersonLenght() {
        return personLenght;
    }

    /**
     * a method that retrieves an item from a database by user name
     * @param requiredName name to be found
     * @return if the object is present, it returns it
     */
    public Person getPersonbyFIO(String requiredName) {
        for (int i = 0; i < personLenght; i++) {
            if (requiredName.equals(personBase[i].getFIO())) {
                return personBase[i];
            }
        }
        return null;
    }

    /**
     * a method that removes a user by name
     * @param deletedName the field name of the object to be deleted
     */
    public void deletePersonbyFIO(String deletedName) {
        for (int i = 0; i < personLenght; i++) {
            if (deletedName.equals(personBase[i].getFIO())) {
                for (int j = i; j < personLenght - 1 ; j++) {
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
     * the method implements adding items to the database
     * @param person object to add
     */
    public void addNewPerson(Person person) {

        if (personBase.length == personLenght) {
            Person[] newPersonBase = new Person[personBase.length+5];

            for(int i = 0; i < personBase.length; i++) {
                newPersonBase[i] = personBase[i];
            }
            personBase = newPersonBase;
        }
        if (personLenght == 0 || getPerson(person) == null) {
            Person tempPerson = new Person();
            tempPerson.setValues(person.getFIO(), person.getBirthday(), person.getMonthOfBirth(), person.getYearOfBirthday());
            personBase[personLenght] = tempPerson;
            personLenght++;
        } else {
            System.out.println("Такой пользователь существует в базе данных");
        }

    }




    /**
     * the method that displays the database
     */
    public void showAllDatabase() {
        for (Person item: personBase) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }


}
