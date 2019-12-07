package com.database.base;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SreamApi {
    static void stream(IRepository rep) throws IOException {

        CvsParser.parseBase(rep);
        List<IPerson> parseList = rep.toList();
        Stream<IPerson> personStream = parseList.stream();
        List<IPerson> personNameSalary = personStream.filter( per -> per.getFirstName().substring(0,1).equals("A") &&
                 per.getSalary().intValueExact() > 3000 &&
                per.getAge() > 30).collect(Collectors.toList());

        Stream<IPerson> personStream1 = parseList.stream();
        List<IPerson> personName = personStream1.filter(per -> per
                .getFirstName()
                .substring(0,2).toLowerCase()
                .equals("aa")).collect(Collectors.toList());

        Stream<IPerson> personStream2 = parseList.stream();

        Map<IDivision, Long> departmentSalary = personStream2.collect(
                Collectors.groupingBy(IPerson::getDivision, Collectors.summingLong(per -> per
                        .getSalary()
                        .intValueExact())));

        Stream<IPerson> personStream3 = parseList.stream();
        Map<IDivision, Long> departmentPersons = personStream3.collect(Collectors.groupingBy(
                IPerson::getDivision,Collectors.counting()));





    }

}

