package com.database.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WebServiceInterface {

    @WebMethod
    public String getUserNameById(int id);

    @WebMethod
    public Integer getUserAgeById(int id);

    @WebMethod
    public String hello();
}
