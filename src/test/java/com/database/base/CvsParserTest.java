package com.database.base;


import com.database.base.CvsParser;
import com.database.base.Factory;
import org.junit.jupiter.api.Test;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import java.io.IOException;

class CvsParserTest {

    @Test
    void parseBase() throws IOException {
        Factory factory = new Factory();
        IRepository<IPerson> base = factory.<IPerson>createRepository(IPerson.class);
        CvsParser.parseBase(base);
    }
}