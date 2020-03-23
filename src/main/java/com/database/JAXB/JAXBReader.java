package com.database.JAXB;

import com.database.base.*;
import com.database.sort.BubbleSorter;
import com.sun.jmx.mbeanserver.Repository;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class JAXBReader {

    private static final Logger LOG = LoggerFactory.getLogger(JAXBReader.class);

    public Base read() {
        LOG.debug("[ read: ");
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class, Division.class, Base.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Base<Person> base = (Base<Person>) unmarshaller.unmarshal(new File("./file.xml"));
            LOG.debug("] return : {}", base);
            return base;
        } catch (JAXBException e) {
            LOG.error("[JAXBException: {}]", e);
            e.printStackTrace();
            LOG.debug("] ");
        }


        return null;
    }

    public void write() {
        LOG.debug("[ write: ]");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class, Division.class, Base.class);
            File file = new File("./file.xml");
            Factory factory = new Factory();
            IRepository<IPerson> base = factory.createRepository(IPerson.class);
            CvsParser.parseBase(base);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(base, file);
        } catch (JAXBException | IOException e) {
            LOG.error("[JAXBException| IOException: {}]", e);
            e.printStackTrace();
        }
    }



}
