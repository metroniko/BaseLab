package com.database;

import com.database.interfaces.entities.IPerson;
import com.database.interfaces.entities.enums.Gender;
import com.database.interfaces.repository.IRepository;
import com.database.sort.AgeComparator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class BaseTest {

    private final Base base = new Base();
    private final Person[] expectedBase;
    private final Person[] ageBase;
    private final Person[] nameBase;


    BaseTest() {
        Gender gender = Gender.MALE;
        Person person = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse("15.05.1970", formatter);
        Division division = new Division();
        division.setId(1);
        division.setName("name");
        person.setValues("Kolya1","Murzinov",gender,  LocalDate.parse("19.05.1980", formatter), division, new BigDecimal(4200), 12);
        base.add(person);
        person.setValues("Kolya2","Aurzinov",gender,  LocalDate.parse("15.05.1970", formatter), division, new BigDecimal(4200), 12);
        base.add(person);
        person.setValues("Kolya3","Uurzinov",gender,  LocalDate.parse("14.05.1972", formatter), division, new BigDecimal(4200), 12);
        base.add(person);
        person.setValues("Kolya4","Durzinov",gender,  LocalDate.parse("02.05.1970", formatter), division, new BigDecimal(4200), 12);
        base.add(person);
        person.setValues("Kolya5","Vurzinov",gender,  LocalDate.parse("07.05.1970", formatter), division, new BigDecimal(4200), 12);
        base.add(person);
        person.setValues("Kolya6","Kurzinov",gender,  LocalDate.parse("13.05.1979", formatter), division, new BigDecimal(4200), 12);
        base.add(person);

        Person person1 = new Person();
        person1.setValues("Kolya1","Murzinov",gender,  LocalDate.parse("19.05.1980", formatter), division, new BigDecimal(4200), 12);
        Person person2 = new Person();
        person2.setValues("Kolya2","Aurzinov",gender,  LocalDate.parse("15.05.1970", formatter), division, new BigDecimal(4200), 12);
        Person person3 = new Person();
        person3.setValues("Kolya3","Uurzinov",gender,  LocalDate.parse("14.05.1972", formatter), division, new BigDecimal(4200), 12);
        Person person4 = new Person();
        person4.setValues("Kolya4","Durzinov",gender,  LocalDate.parse("02.05.1970", formatter), division, new BigDecimal(4200), 12);
        Person person5 = new Person();
        person5.setValues("Kolya5","Vurzinov",gender,  LocalDate.parse("07.05.1970", formatter), division, new BigDecimal(4200), 12);
        Person person6 = new Person();
        person6.setValues("Kolya6","Kurzinov",gender,  LocalDate.parse("13.05.1979", formatter), division, new BigDecimal(4200), 12);

        expectedBase = new Person[]{person1, person2, person3, person4, person5, person6};


        ageBase = new Person[]{person1, person6, person3, person2, person5, person4};
        nameBase = new Person[]{person2, person4, person6, person1, person3, person5};


    }


    @Test
    void add() {
        IPerson[] actualBase = base.getAllDatabase();
        assertArrayEquals(actualBase, expectedBase);
    }

    @Test
    void get() {
        Gender gender = Gender.MALE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        IPerson person = base.get(0);
        Person person1 = new Person();
        Division division = new Division();
        division.setId(1);
        division.setName("name");
        person1.setValues("Kolya1","Murzinov",gender,  LocalDate.parse("19.05.1980", formatter), division, new BigDecimal(4200), 12);
        assertEquals(person, person1);
    }

    @Test
    void delete() {
        base.delete(0);
        Gender gender = Gender.MALE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Division division = new Division();
        division.setId(1);
        division.setName("name");
        Person person2 = new Person();
        person2.setValues("Kolya2","Aurzinov",gender,  LocalDate.parse("15.05.1970", formatter), division, new BigDecimal(4200), 12);
        Person person3 = new Person();
        person3.setValues("Kolya3","Uurzinov",gender,  LocalDate.parse("14.05.1972", formatter), division, new BigDecimal(4200), 12);
        Person person4 = new Person();
        person4.setValues("Kolya4","Durzinov",gender,  LocalDate.parse("02.05.1970", formatter), division, new BigDecimal(4200), 12);
        Person person5 = new Person();
        person5.setValues("Kolya5","Vurzinov",gender,  LocalDate.parse("07.05.1970", formatter), division, new BigDecimal(4200), 12);
        Person person6 = new Person();
        person6.setValues("Kolya6","Kurzinov",gender,  LocalDate.parse("13.05.1979", formatter), division, new BigDecimal(4200), 12);
        IPerson[] personBases = new IPerson[] {person2, person3, person4, person5, person6};
        IPerson[] newbase = base.getAllDatabase();
        assertArrayEquals(personBases, newbase);
    }

    @Test
    void set() {
        Gender gender = Gender.MALE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Division division = new Division();
        division.setId(1);
        division.setName("name");
        Person person = new Person();
        person.setValues("NeKolya2","Murzinov",gender,  LocalDate.parse("15.05.1970", formatter), division, new BigDecimal(4200), 12);
        base.set(1,person);

        assertEquals(person, base.get(1));

    }

    @Test
    void toList() {
        List<IPerson> iPersonList = base.toList();
        assertEquals(iPersonList, Arrays.asList(expectedBase));
    }

    @Test
    void sortBy() {
        Comparator<IPerson> comparator = new AgeComparator();
        base.sortBy(comparator);
        assertArrayEquals(ageBase, base.getAllDatabase());
    }
    @Test
    void searchBy() {
        Predicate<IPerson> personSearch = p -> p.getBirthdate().getYear() > 1974;

        IRepository newBase =  base.searchBy(personSearch);
        assertEquals(newBase, base);
    }

    @Test
    void bubbleSortedByAge() {
        base.bubbleSortedByAge();
        IPerson[] requiredBase = base.getAllDatabase();
        assertArrayEquals(requiredBase, ageBase);
    }

    @Test
    void bubbleSortByFIO() {
        base.bubbleSortByFIO();
        IPerson[] requiredBase = base.getAllDatabase();
        assertArrayEquals(requiredBase, nameBase);
    }

    @Test
    void insertSorterByAge() {
        base.insertSorterByAge();
        IPerson[] requiredBase = base.getAllDatabase();
        assertArrayEquals(requiredBase, ageBase);
    }

    @Test
    void insertSorterByFio() {
        base.insertSorterByFio();
        IPerson[] requiredPerson = base.getAllDatabase();
        assertArrayEquals(requiredPerson, nameBase);
    }

    @Test
    void testAdd() {
        Gender gender = Gender.MALE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Division division = new Division();
        division.setId(1);
        division.setName("name");
        Person person5 = new Person();
        person5.setValues("Kolya55","Vurzinov",gender,  LocalDate.parse("07.05.1970", formatter), division, new BigDecimal(4200), 12);
        base.add(1, person5);
        assertEquals(base.get(1), person5);

    }
}