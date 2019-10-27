package com.database;

import org.joda.time.LocalDate;
import java.util.Objects;

/**
 * a class that stores parameters about a user.
 */
public class Person {

    /**
     * this variable stores the name surname and patronymic of the user.
     */
    private String fio;

    /**
     * this variable stores the gender.
     */
    private String gender;

    /**
     * this variable stores the birthday.
     */
    private int birthday;

    /**
     * this variable stores the month of birth.
     */
    private int monthOfBirth;

    /**
     * this variable stores the year of birth.
     */
    private int yearOfBirthday;

    /**
     * this variable stores the user id.
     */
    private int id;

    /**
     * returns FIO.
     * @return name surname and patronymic.
     */
    public final String getFio() {
        return fio;
    }

    /**
     *
     * @return birthday
     */
    public final int getBirthday() {
        return birthday;
    }

    /**
     *
     * @return monthOfBirth
     */
    public final int getMonthOfBirth() {
        return monthOfBirth;
    }

    /**
     *
     * @return yearOfBirthday
     */
    public final int getYearOfBirthday() {
        return yearOfBirthday;
    }

    /**
     *
     * @return yearOfBirthday
     */
    public final int getId() {
        return id;
    }

    /**
     * the method calculates the age of a person by the given field fields.
     */
    public final void getLifetime() {

        LocalDate date = LocalDate.now().
                minusYears(yearOfBirthday).
                minusMonths(monthOfBirth).
                minusDays(birthday);

        System.out.println("years: "
                + date.getYear()
                + " months: "
                + date.getMonthOfYear()
                + " days: "
                + date.getDayOfMonth());

    }

    /**
     * return gender.
     * @return gender
     */
    public final String getGender() {
        return gender;
    }

    /**
     * set values.
     * @param reqFIO FIO
     * @param reqGender gender
     * @param reqBirthday birthday
     * @param reqMonthOfBirth month of birth
     * @param reqYearOfBirthday year of birth
     */
    public final void setValues(final String reqFIO,
                          final String reqGender,
                          final int reqBirthday,
                          final int reqMonthOfBirth,
                          final int reqYearOfBirthday) {
        this.fio = reqFIO;
        this.birthday = reqBirthday;
        this.monthOfBirth = reqMonthOfBirth;
        this.yearOfBirthday = reqYearOfBirthday;
        this.gender = reqGender;
        this.id = -1 * hashCode();
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return birthday == person.birthday
                && monthOfBirth == person.monthOfBirth
                && yearOfBirthday == person.yearOfBirthday
                && Objects.equals(fio, person.fio)
                && Objects.equals(gender, person.gender);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(
                fio,
                gender,
                birthday,
                monthOfBirth,
                yearOfBirthday);
    }


    @Override
    public final String toString() {
        return "Person{"
                + "FIO='" + fio + '\''
                + ", gender='" + gender + '\''
                + ", birthday=" + birthday
                + ", monthOfBirth=" + monthOfBirth
                + ", yearOfBirthday=" + yearOfBirthday
                + ", id="
                + id
                + '}';
    }
}
