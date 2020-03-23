package com.database.base;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;


public class Factory implements ILabFactory {
    @Override
    public IPerson createPerson() {
        return new Person();
    }

    @Override
    public IDivision createDivision() {
        return new Division();
    }

    @Override
    public <T> IRepository<T> createRepository(Class<T> clazz) {
        return new Base<>();
    }

    @Override
    public IPersonRepository createPersonRepository() {
        return null;
    }
}
