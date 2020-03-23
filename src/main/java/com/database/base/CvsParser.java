package com.database.base;

import au.com.bytecode.opencsv.CSVReader;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IRepository;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * класс для парсинга файла в объект
 */
public class CvsParser {
    private static final Factory factory = new Factory();

    private static final Logger LOG = LoggerFactory.getLogger(CvsParser.class);

    /**
     * класс парсит базу данных из csv файла
     * @param base объект базы, в которую нужно спарсить
     * @throws IOException при невозможнот найти файл
     */
    public static void parseBase(final IRepository<IPerson> base) throws IOException {
        LOG.debug("[ parseBase: {} ]", base);
        final CSVReader reader = new CSVReader(new
                FileReader(
                "src\\main\\resources\\persons.csv"),
                ';'
                ,'"' ,
                1);

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
            IPerson person =  factory.createPerson();
            ((Person)person).setValues(firstName, lastName, gender, date, division, salary, id);
            base.add(person);
        }
    }

    /**
     * метод для проверки повторяюшихся депортпаментов
     * @param divisionName имя депортамента
     * @return объект депортамента
     */
    static private IDivision checkArrayDivision(String divisionName) {
        LOG.debug("[ checkArrayDivision: {}", divisionName);
        for (IDivision currentDivision: Person.alldDivision) {
            if(currentDivision.getName().equals(divisionName)) {
                LOG.debug("] return : {}", currentDivision);
                return currentDivision;
            }
        }
        IDivision newDivision = factory.createDivision() ;
        newDivision.setName(divisionName);
        newDivision.setId(Person.alldDivision.size() + 1);
        Person.alldDivision.add(newDivision);
        LOG.debug("] return : {}", newDivision);
        return newDivision;
    }
}
