package com.database.base;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * класс для преобразования работы
 * с контейнером
 * при помощи streamApi.
 * @param <T> тип который будет содержать
 *           контейнер.
 */
public class StreamApi<T extends IPerson> {
    /**
     * контейнер который нужно будет
     * распарсить.
     */
    private List<T> parseList;

    /**
     * конструктор в котором парсится
     * база данных.
     * @param rep объект, dв котроый будут
     *            записываться
     *            данные.
     * @throws IOException исключение.
     */
    public StreamApi(final IRepository rep) throws IOException {
        CvsParser.parseBase(rep);
        this.parseList = rep.toList() ;
    }

    /**
     * метод отбирает пользователей у которых
     * первая буква "A" зарплата Ю 3000
     * и возраст > 30.
     * @return  отфильтрованный контейнер.
     */
    public List<T> streamPersonNameSalary() {
        Stream<T> personStream = parseList.stream();

        return personStream.filter(per -> per.getFirstName()
                .substring(0,1).equals("A") &&
                per.getSalary().intValueExact() > 3000 &&
                per.getAge() > 30).collect(Collectors.toList());
    }

    /**
     * метод фильтркет пользователей у которых
     * первые 2 буквы "аа".
     * @return отфильтрованный контейнер.
     */
    public List<T> streamPersonName() {
        Stream<T> personStream = parseList.stream();
        return personStream.filter(per -> per
                .getFirstName()
                .substring(0,2).toLowerCase()
                .equals("aa")).collect(Collectors.toList());
    }

    /**
     * Сортирует пользователей по
     * отделениям и общей зарплаты сотрудников.
     * @return отфильтрованный контейнер
     */
    public  Map<? extends IDivision, Long> streamPersonSalary() {
        Stream<T> personStream = parseList.stream();

        return personStream.collect(
                Collectors.groupingBy(IPerson::getDivision,
                        Collectors.summingLong(per -> per
                        .getSalary()
                        .intValueExact())));
    }
    /**
     * Сортирует пользователей по
     * отделениям и количеству людей в отделении.
     * @return отфильтрованный контейнер.
     */
    public Map<? extends IDivision, Long> streamDepartmentPersons() {
        Stream<T> personStream = parseList.stream();
        return personStream.collect(Collectors.groupingBy(
                IPerson::getDivision,Collectors.counting()));
    }

    /**
     * Группирует пользователей по возросту.
     * @return отфильтрованный контейнер.
     */
    public Map<Integer, Long> streamPersonAge() {
        Stream<T> personStream = parseList.stream();
        return personStream.collect(Collectors.groupingBy(
                IPerson::getAge,Collectors.counting()
        ));
    }

}

