package com.database;

import com.database.interfaces.repository.IRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CvsParserTest {

    @Test
    void parseBase() throws IOException {
        Base base = new Base();
        CvsParser.parseBase(base);
    }
}