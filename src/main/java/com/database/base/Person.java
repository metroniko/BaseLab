package com.database.base;

import com.database.JAXB.DivisionAdapter;
import com.database.JAXB.LocalDateAdapter;
import org.joda.time.LocalDate;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * a class that stores parameters about a user.
 */
@XmlRootElement(name = "person")
public class Person implements IPerson {
    /**
     * подразделение.
     */
    private Division division;
    /**
     * имя.
     */
    private String firstName;
    /**
     * зарплата.
     */
    private BigDecimal salary;
    /**
     * фамилия.
     */
    private String lastName;
    /**
     * день рождения.
     */

    private java.time.LocalDate birthdate;
    /**
     * массив подразделений.
     */

    static final List<IDivision> alldDivision = new ArrayList<>();

    @Override
    public final Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer idreq) {
        this.id = idreq;
    }

    @Override
    public final void setFirstName(final String firstNamereq) {
        this.firstName = firstNamereq;
    }

    @Override
    public final void setLastName(final String firstNameReq) {
        this.lastName = firstNameReq;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Override
    public final java.time.LocalDate getBirthdate() {
        return this.birthdate;
    }

    @Override
    public final void setBirthdate(final java.time.LocalDate birthdateReq) {
        this.birthdate = birthdateReq;
    }

    @Override
    public final Integer getAge() {
        return LocalDate.now().minusYears(birthdate.getYear()).getYear();
    }

    @Override
    public final Gender getGender() {
        return this.gender;
    }

    @Override
    public final void setGender(final Gender genderReq) {
        this.gender = genderReq;
    }

    @XmlElement(type = Division.class)
    @Override
    public final IDivision getDivision() {
        return division;
    }

    @Override
    final public void setDivision(final IDivision divisionReq) {
        this.division = (Division) divisionReq;
    }

    @Override
    public final BigDecimal getSalary() {
        return this.salary;
    }

    @Override
    public final void setSalary(final BigDecimal salaryReq) {
        this.salary = salaryReq;
    }

    /**
     * this variable stores the name surname and patronymic of the user.
     */


    public final String getLastName() {
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
     * этот метод возвращает время жизни пользователя
     * @return объект даты
     */
    public final LocalDate getLifetime() {
        return LocalDate.now().
                minusYears(birthdate.getYear()).
                minusMonths(birthdate.getMonthValue()).
                minusDays(birthdate.getDayOfMonth());
    }


    /**
     * метод для задачи значений
     * @param reqfirstNameParam имя.
     * @param reqLastNameParam фамилия.
     * @param reqGenderParam пол.
     * @param birthdateParam день ролждения.
     * @param divisionParam отделение.
     * @param salaryParam зарплaтa.
     * @param idParam идентификатор.
     */
    final void setValues(final String reqfirstNameParam,
                            final String reqLastNameParam,
                            final Gender reqGenderParam,
                            final java.time.LocalDate birthdateParam,
                            final IDivision divisionParam,
                            final BigDecimal salaryParam,
                            final Integer idParam
                                ) {
        this.firstName = reqfirstNameParam;
        this.lastName = reqLastNameParam;
        this.birthdate = birthdateParam;
        this.division = (Division) divisionParam;
        this.gender = reqGenderParam;
        this.salary = salaryParam;
        this.id = idParam;

    }

    @Override
    public final String toString() {
        return "Person{"
                + "division="
                + division
                + ", firstName='"
                + firstName
                + '\''
                + ", salary="
                + salary
                + ", lastName='"
                + lastName
                + '\''
                + ", birthdate="
                + birthdate
                + ", gender="
                + gender
                + ", id="
                + id
                + '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id
                && Objects.equals(division, person.division)
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(salary, person.salary)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(birthdate, person.birthdate)
                && gender == person.gender;
    }
    @Override
    public final int hashCode() {
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
