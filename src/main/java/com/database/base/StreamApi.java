package com.database.base;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi<T extends  IPerson> {
    private List<T> parseList;

    public StreamApi(IRepository rep) throws IOException {
        CvsParser.parseBase(rep);
        this.parseList =  rep.toList() ;
    }

    public List<T> streamPersonNameSalary() {
        Stream<T> personStream = parseList.stream();
        List<T> personNameSalary = personStream.filter( per -> per.getFirstName().substring(0,1).equals("A") &&
                per.getSalary().intValueExact() > 3000 &&
                per.getAge() > 30).collect(Collectors.toList());

        return personNameSalary;
    }

    public List<T> streamPersonName() {
        Stream<T> personStream = parseList.stream();
        List<T> personName = personStream.filter(per -> per
                .getFirstName()
                .substring(0,2).toLowerCase()
                .equals("aa")).collect(Collectors.toList());
        return personName;
    }
    public  Map<? extends  IDivision, Long> streamPersonSalary() {
        Stream<T> personStream = parseList.stream();

        Map<IDivision, Long> departmentSalary = personStream.collect(
                Collectors.groupingBy(IPerson::getDivision, Collectors.summingLong(per -> per
                        .getSalary()
                        .intValueExact())));

        return departmentSalary;
    }
    public Map<? extends  IDivision, Long> streamDepartmentPersons () {
        Stream<T> personStream = parseList.stream();
        Map<IDivision, Long> departmentPersons = personStream.collect(Collectors.groupingBy(
                IPerson::getDivision,Collectors.counting()));
        return departmentPersons;
    }
    public Map<Integer, Long> streamPersonAge() {
        Stream<T> personStream = parseList.stream();
        Map<Integer, Long> personAge = personStream.collect(Collectors.groupingBy(
                IPerson::getAge,Collectors.counting()
        ));
        return personAge;
    }

}

