package com.database;

import com.database.interfaces.entities.IDivision;
import com.database.interfaces.entities.IPerson;
import com.database.interfaces.entities.enums.Gender;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * a class that stores parameters about a user.
 */
public class Person implements IPerson {

    private IDivision division;
    private String firstName;
    private BigDecimal salary;
    private String lastName;
    private java.time.LocalDate birthdate;

    @Override
    public Integer getId() { return this.id; }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String firstName) {
        this.lastName = firstName;
    }

    @Override
    public java.time.LocalDate getBirthdate() {
        return this.birthdate;
    }

    @Override
    public void setBirthdate(java.time.LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public Integer getAge() {
        return LocalDate.now().minusYears(birthdate.getYear()).getYear();
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public IDivision getDivision() {
        return division;
    }

    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    @Override
    public BigDecimal getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * this variable stores the name surname and patronymic of the user.
     */


    public String getLastName() {
        return lastName;
    }

    /**
     * this variable stores the gender.
     */
    private Gender gender;

    /**
     * this variable stores the user id.
     */
    private int id;

    /**
     * returns FIO.
     * @return name surname and patronymic.
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return birthday
     */
    public final java.time.LocalDate getBirthday() {
        return birthdate;
    }

    /**
     * the method calculates the age of a person by the given field fields.
     */
    public final LocalDate getLifetime() {
        return LocalDate.now().
                minusYears(birthdate.getYear()).
                minusMonths(birthdate.getMonthValue()).
                minusDays(birthdate.getDayOfMonth());
    }


    /**
     * set values.
     * @param reqfirstName first name
     * @param reqLastName last Name
     * @param reqGender gender
     */
    final void setValues(final String reqfirstName,
                            final String reqLastName,
                            final Gender reqGender,
                            final java.time.LocalDate birthdate,
                            final IDivision division,
                            final BigDecimal salary,
                            final Integer id
                                ) {
        this.firstName = reqfirstName;
        this.lastName = reqLastName;
        this.birthdate = birthdate;
        this.division = division;
        this.gender = reqGender;
        this.salary = salary;
        this.id = id;

    }

    @Override
    public String toString() {
        return "Person{" +
                "division=" + division +
                ", firstName='" + firstName + '\'' +
                ", salary=" + salary +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(division, person.division) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(salary, person.salary) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(birthdate, person.birthdate) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                division,
                firstName,
                salary,
                lastName,
                birthdate,
                gender,
                id);
    }
}
