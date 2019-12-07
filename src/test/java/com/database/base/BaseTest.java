package com.database.base;


import com.database.sort.AgeComparator;
import org.junit.jupiter.api.Test;
import reflection.Injector;
import reflection.InjectorExeption;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

 class BaseTest {
    private final Factory factory = new Factory();
    private final IRepository<IPerson> base = factory.createRepository(IPerson.class);
    private final IPerson[] expectedBase;
    private final IPerson[] ageBase;


     BaseTest() {
        Gender gender = Gender.MALE;
        Person person = (Person) factory.createPerson();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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



    }


    @Test
    void add() {
        IPerson[] actualBase = ((Base<IPerson>)base).getAllDatabase();
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
        IPerson[] newbase = ((Base<IPerson>)base).getAllDatabase();
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
    void sortBy() throws  InjectorExeption {
        AgeComparator<IPerson> comparator = new AgeComparator<>();
        Injector.inject(base);
        base.sortBy(comparator);
        assertArrayEquals(ageBase, ((Base<IPerson>)base).getAllDatabase());
    }
    @Test
    void searchBy() {
        Predicate<IPerson> personSearch = p -> p.getBirthdate().getYear() > 1974;

        IRepository<IPerson> newBase =  base.searchBy(personSearch);
        assertEquals(newBase, base);
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