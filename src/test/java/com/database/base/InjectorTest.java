package com.database.base;


import com.database.base.Division;
import com.database.base.Factory;
import com.database.base.Person;
import org.junit.jupiter.api.Test;
import reflection.Injector;
import reflection.InjectorExeption;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class InjectorTest {
    private final Factory factory = new Factory();
    private final IRepository<IPerson> base = factory.createRepository(IPerson.class);



    @Test
    void inject() throws  InjectorExeption {
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
        Injector.inject(base);
    }
}