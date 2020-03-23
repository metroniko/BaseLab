package com.database.JAXB;

import com.database.base.Division;
import ru.vsu.lab.entities.IDivision;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DivisionAdapter extends XmlAdapter<String, IDivision> {
    public DivisionAdapter() {
    }

    @Override
    public IDivision unmarshal(String v) throws Exception {
        return null;
    }

    @Override
    public String marshal(IDivision v) throws Exception {
        return v.toString();
    }
}
