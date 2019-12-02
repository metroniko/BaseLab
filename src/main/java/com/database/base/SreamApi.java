package com.database.base;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SreamApi {
    static void stream(IRepository rep) throws IOException {

        CvsParser.parseBase(rep);
        List<IPerson> parseList = rep.toList();
        Stream<IPerson> personStream = parseList.stream();
        List<IPerson> personNameSalary = personStream.filter( per -> per.getFirstName().substring(1).equals("A") &&
                 per.getSalary().intValueExact() > 3000 &&
                per.getAge() > 30).collect(Collectors.toList());
        Stream<IPerson> personStream1 = parseList.stream();
        personStream1.filter(per -> per.getFirstName().substring(2).equals("Aa")).collect(Collectors.toList());





    }
}

