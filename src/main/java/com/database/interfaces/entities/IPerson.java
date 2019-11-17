package com.database.interfaces.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.database.interfaces.entities.enums.Gender;

public interface IPerson {
	
	Integer getId();
	
	void setId(Integer id);
	
	String getFirstName();
	
	void setFirstName(String firstName);
	
	String getLastName();
	
	void setLastName(String firstName);
	
	LocalDate getBirthdate();
	
	void setBirthdate(LocalDate birthdate);
	
	Integer getAge();
	
	Gender getGender();
	
	void setGender(Gender gender);
	
	IDivision getDivision();
	
	void setDivision(IDivision division);
	
	BigDecimal getSalary();
	
	void setSalary(BigDecimal salary);
	
	

}
