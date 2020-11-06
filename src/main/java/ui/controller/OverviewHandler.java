package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OverviewHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        request.setAttribute("db", db.getPersons());

        Person person = (Person) request.getSession().getAttribute("person");
        request.setAttribute("user",person);
        if(person!=null){
            request.setAttribute("reservations", db.getReservationsUser(person.getUserid()));
            return "personoverview.jsp";
        }
        request.setAttribute("reservations",null);
        return "personoverview.jsp";

    }
}
