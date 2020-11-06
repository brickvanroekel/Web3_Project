package ui.controller;

import domain.model.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
