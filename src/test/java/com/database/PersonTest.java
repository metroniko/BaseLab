package com.database;

import org.junit.jupiter.api.Test;
import org.joda.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getLifetime() {
        Person person = new Person();
        person.setValues(("Dolya1"), "male", 12, 7, 2014);
        LocalDate date = new LocalDate().minusDays(12).minusMonths(7).minusYears(2014);
        assertEquals(date, person.getLifetime());
    }
}