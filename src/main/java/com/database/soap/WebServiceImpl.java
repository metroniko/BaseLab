package com.database.soap;


import com.database.base.Base;
import com.database.base.CvsParser;
import com.database.base.Factory;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import javax.jws.WebService;
import java.io.IOException;

@WebService(endpointInterface = "com.database.soap.WebServiceInterface")
public class WebServiceImpl implements WebServiceInterface {

    public IRepository<IPerson> base;

    public WebServiceImpl() throws IOException {
        Factory factory = new Factory();
        this.base = factory.createRepository(IPerson.class);
        CvsParser.parseBase(base);
    }

    @Override
    public String getUserNameById(int id) {
        IPerson person =  base.get(id);
        return person.getFirstName();
    }

    @Override
    public Integer getUserAgeById(int id) {
        IPerson person =  base.get(id);
        return person.getAge();

    }

    @Override
    public String hello() {
        return "hello";
    }
}
