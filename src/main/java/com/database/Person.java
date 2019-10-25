package com.database;

import org.joda.time.LocalDate;
import java.util.Objects;

/**
 * a class that stores parameters about a user
 */
public class Person {

    /**
     * this variable stores the name surname and patronymic of the user
     */
    private String FIO;
    /**
     * this variable stores the birthday
     */

    private int birthday;

    /**
     * this variable stores the month of birth
     */
    private int monthOfBirth;

    /**
     * this variable stores the year of birth
     */
    private int yearOfBirthday;

    /**
     * this variable stores the user id
     */
    private int id;

    /**
     * returns FIO
     * @return name surname and patronymic
     */
    public String getFIO() {
        return FIO;
    }

    /**
     *
     * @return birthday
     */
    public int getBirthday() {
        return birthday;
    }

    /**
     *
     * @return monthOfBirth
     */
    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    /**
     *
     * @return yearOfBirthday
     */
    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    /**
     *
     * @return yearOfBirthday
     */
    public int getId() {
        return id;
    }


    public void getLifetime() {

        LocalDate date = LocalDate.now().minusYears(yearOfBirthday).minusMonths(monthOfBirth).minusDays(birthday);
        System.out.println("years: "+date.getYear()+" months: "+date.getMonthOfYear() + " days: "+ date.getDayOfMonth());

    }


    public void setValues(String FIO, int birthday, int monthOfBirth, int yearOfBirthday) {
        this.FIO = FIO;
        this.birthday = birthday;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirthday = yearOfBirthday;
        this.id = (new Base().getPersonLenght()) + 1;

    }

    /**
     * the method calculates the age of a person by the given field fields
     */




    @Override
    public String toString() {
        return "Person{" +
                "FIO='" + FIO + '\'' +
                ", birthday=" + birthday +
                ", monthOfBirth=" + monthOfBirth +
                ", yearOfBirthday=" + yearOfBirthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return birthday == person.birthday &&
                monthOfBirth == person.monthOfBirth &&
                yearOfBirthday == person.yearOfBirthday &&
                Objects.equals(FIO, person.FIO);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(FIO, birthday, monthOfBirth, yearOfBirthday);
    }
}
