package com.database.base;

import org.junit.jupiter.api.Test;
import ru.vsu.lab.repository.IRepository;

class FactoryTest {
    @Test
    void createRepository() {
        Factory factory = new Factory();
        IRepository base = factory.createRepository(Person.class);
    }
}