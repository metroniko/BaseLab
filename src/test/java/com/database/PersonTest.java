package com.database;

import org.junit.jupiter.api.Test;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getAge() {
        Gender gender = Gender.MALE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Division division = new Division();
        division.setId(1);
        division.setName("name");
        Person person = new Person();
        person.setValues("Kolya1","Murzinov",gender,  LocalDate.parse("06.06.1999", formatter), division, new BigDecimal(4200), 12);
        assertEquals(20, person.getAge());

    }


}