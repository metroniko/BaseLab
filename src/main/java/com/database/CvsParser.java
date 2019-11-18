package com.database;

import au.com.bytecode.opencsv.CSVReader;
import com.database.interfaces.entities.IDivision;
import com.database.interfaces.entities.enums.Gender;
import com.database.interfaces.repository.IRepository;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class CvsParser {
    static void parseBase(IRepository base) throws IOException {
        CSVReader reader = new CSVReader(new FileReader("src\\main\\resourses\\persons.csv"), ';' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            int id;
            String firstName;
            String lastName;
            Gender gender;
            LocalDate date;
            IDivision division;
            BigDecimal salary;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            id = Integer.parseInt(nextLine[0]);
            firstName = nextLine[1];
            lastName = nextLine[1];
            if (nextLine[2].compareTo("Male") > 0) {
                gender = Gender.MALE;
            } else {
                gender = Gender.FEMALE;
            }
            division = checkArrayDivision(nextLine[4]);
            date = LocalDate.parse(nextLine[3], formatter);
            salary = new BigDecimal(nextLine[5]);
            Person person = new Person();
            person.setValues(firstName, lastName, gender, date, division, salary, id);
            base.add(person);
        }
    }
    static private IDivision checkArrayDivision(String divisionName) {
        for (IDivision currentDivision: Person.alldDivision) {
            if(currentDivision.getName().equals(divisionName)) {
                return currentDivision;
            }
        }
        IDivision newDivision = new Division();
        newDivision.setName(divisionName);
        newDivision.setId(Person.alldDivision.size() + 1);
        Person.alldDivision.add(newDivision);
        return newDivision;
    }
}
