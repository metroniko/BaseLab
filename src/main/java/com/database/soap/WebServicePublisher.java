package com.database.soap;

import javax.xml.ws.Endpoint;
import java.io.IOException;

public class WebServicePublisher {
    public static void main(String[] args) throws IOException {
        Endpoint.publish("http://localhost:1986/wss/hello", new WebServiceImpl());
    }
}
