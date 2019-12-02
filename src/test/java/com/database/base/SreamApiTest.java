package com.database.base;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SreamApiTest {

    @Test
    void stream() throws IOException {
        Base base = new Base();
        SreamApi.stream(base);
    }
}