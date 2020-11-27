package ui.controller;

import domain.service.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class RequestHandler {
    protected ContactTracingService db;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException;


    public void setDb(ContactTracingService service){
        this.db = service;
    }
    public ContactTracingService getDb() {
        return db;
    }



}
