package Database;

import org.joda.time.LocalDate;
import java.util.Objects;

/**
 * a class that stores parameters about a user
 */
public class Person {


    private String FIO;
    private int birthday;
    private int monthOfBirth;
    private int yearOfBirthday;

    private int id;

    public String getFIO() {
        return FIO;
    }

    public int getBirthday() {
        return birthday;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

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
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return birthday == person.birthday &&
                monthOfBirth == person.monthOfBirth &&
                yearOfBirthday == person.yearOfBirthday &&
                Objects.equals(FIO, person.FIO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FIO, birthday, monthOfBirth, yearOfBirthday);
    }
}
