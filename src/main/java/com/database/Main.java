package com.database;


import com.database.JAXB.JAXBReader;
import com.database.base.Base;
import com.database.base.Person;
import com.database.sort.AgeComparator;
import reflection.Injector;
import reflection.InjectorExeption;
import ru.vsu.lab.entities.IPerson;

import java.util.stream.IntStream;

/**
 *  starting point of the program.
 */
public class Main {
    public static void main(String[] args) throws InjectorExeption {
        JAXBReader jaxbReader = new JAXBReader();
        jaxbReader.write();
        Base<Person> base =  jaxbReader.read();
        AgeComparator<IPerson> comparator = new AgeComparator<>();
        Injector.inject(base);
        base.sortBy(comparator);
    }
}
