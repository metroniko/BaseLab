package com.database.base;

import org.junit.jupiter.api.Test;
import ru.vsu.lab.entities.IPerson;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StreamApiTest {

    @Test
    void stream() throws IOException {
        Base base = new Base();
        StreamApi<IPerson> streamApi = new StreamApi<>(base);
        streamApi.streamDepartmentPersons();
        streamApi.streamPersonName();
        streamApi.streamPersonNameSalary();
        streamApi.streamPersonSalary();
        streamApi.streamPersonAge();
    }
}