package com.database.soap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class WebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:1986/wss/hello?wsdl");
        QName qname = new QName("http://soap.database.com/", "WebServiceImplService");

        // Теперь мы можем дотянуться до тега service в wsdl описании,
        Service service = Service.create(url, qname);
        // а далее и до вложенного в него тега port, чтобы
        // получить ссылку на удаленный от нас объект веб-сервиса
        WebServiceInterface webService = service.getPort(WebServiceInterface.class);

        System.out.println(webService.getUserNameById(1));
    }
}
