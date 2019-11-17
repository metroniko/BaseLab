package com.database.interfaces.repository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.database.interfaces.entities.*;

public interface IRepository {
	
	void add(IPerson person);
	
	IPerson get(int index);
	
	IPerson delete(int index);

	IPerson set(int index, IPerson person);
	
	void add(int index, IPerson person);
	
	List<IPerson> toList();
	
	//Should not use toList method
	void sortBy(Comparator<IPerson> comparator);
	
	//Should not use toList method
	IRepository searchBy(Predicate<IPerson> condition);
	
}
