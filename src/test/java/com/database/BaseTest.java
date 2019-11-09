package com.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseTest {

    private final Base base = new Base();
    private final Person[] expectedBase;
    private final Person[] ageBase;
    private final Person[] nameBase;


    BaseTest() {
        Person person = new Person();
        person.setValues(("Dolya1"), "male", 12, 7, 2014);
        base.addNewPerson(person);
        person.setValues(("Aolya2"), "male", 12, 7, 2013);
        base.addNewPerson(person);
        person.setValues(("Lolya3"), "male", 12, 7, 2012);
        base.addNewPerson(person);
        person.setValues(("Zolya4"), "male", 12, 7, 2011);
        base.addNewPerson(person);
        person.setValues(("Golya5"), "male", 12, 6, 2012);
        base.addNewPerson(person);
        person.setValues(("Nolya6"), "male", 12, 7, 2010);
        base.addNewPerson(person);

        Person person1 = new Person();
        person1.setValues(("Dolya1"), "male", 12, 7, 2014);
        Person person2 = new Person();
        person2.setValues(("Aolya2"), "male", 12, 7, 2013);
        Person person3 = new Person();
        person3.setValues(("Lolya3"), "male", 12, 7, 2012);
        Person person4 = new Person();
        person4.setValues(("Zolya4"), "male", 12, 7, 2011);
        Person person5 = new Person();
        person5.setValues(("Golya5"), "male", 12, 6, 2012);
        Person person6 = new Person();
        person6.setValues(("Nolya6"), "male", 12, 7, 2010);
        expectedBase = new Person[]{person1, person2, person3, person4, person5, person6};


        ageBase = new Person[]{person6, person4, person5, person3, person2, person1};
        nameBase = new Person[]{person2, person1, person5, person3, person6, person4};


    }

    @Test
    void deletePersonbyFIO() {
        // смотря как работает может и не отработать полностью нормально другие методы

        Person person1 = new Person();
        person1.setValues(("Dolya1"), "male", 12, 7, 2014);
        Person person2 = new Person();
        person2.setValues(("Aolya2"), "male", 12, 7, 2013);
        Person person3 = new Person();
        person3.setValues(("Lolya3"), "male", 12, 7, 2012);
        Person person5 = new Person();
        person5.setValues(("Golya5"), "male", 12, 6, 2012);
        Person person6 = new Person();
        person6.setValues(("Nolya6"), "male", 12, 7, 2010);
        Person[] expectedBases = {person1, person2, person3, person5, person6};
        base.deletePersonbyFIO("Zolya4");
        Person[] actualBase =  base.getAllDatabase();

        assertArrayEquals(actualBase, expectedBases);

    }

    @Test
    void addNewPerson() {
        Person[] actualBase = base.getAllDatabase();
        assertArrayEquals(actualBase, expectedBase);
    }


    @Test
    void bubbleSortedByAge() {
        base.bubbleSortedByAge();
        Person[] actualBase = base.getAllDatabase();
        assertArrayEquals(ageBase, actualBase);
    }

    @Test
    void bubbleSortByFIO() {
        base.bubbleSortByFIO();
        Person[] actualBase = base.getAllDatabase();
        assertArrayEquals(nameBase, actualBase);
    }

    @Test
    void insertSorterByAge() {
        base.insertSorterByAge();
        Person[] actualBase = base.getAllDatabase();
        assertArrayEquals(ageBase, actualBase);
    }

    @Test
    void insertSorterByFio() {
        base.insertSorterByFio();
        Person[] actualBase = base.getAllDatabase();
        assertArrayEquals(nameBase,actualBase);
    }

    @Test
    void getAllDatabase() {

        Base base = new Base();
        Person person = new Person();
        person.setValues(("Dolya1"), "male", 12, 7, 2014);
        base.addNewPerson(person);
        Person[] actualBase = base.getAllDatabase();
        Person[] expectedBase = new Person[] {person};
        assertArrayEquals(actualBase, expectedBase);
    }

    @Test
    void getPersonbyFIO() {
        Person person = new Person();
        person.setValues(("Dolya1"), "male", 12, 7, 2014);
        assertEquals(person, base.getPersonbyFIO("Dolya1").get());
    }

    @Test
    void getPersonByBirthday() {
        Person person = new Person();
        person.setValues(("Dolya1"), "male", 12, 7, 2014);
        assertEquals(person, base.getPersonByBirthday(2014,7,12).get());
    }
}