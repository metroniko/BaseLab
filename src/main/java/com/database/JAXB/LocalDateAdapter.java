package com.database.JAXB;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final Logger LOG = LoggerFactory.getLogger(LocalDateAdapter.class);

    public LocalDateAdapter() {
    }

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        LOG.debug("[ read: {} ]", v);
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        LOG.debug("[ marshal: {} ]", v);
        return v.toString();
    }
}
