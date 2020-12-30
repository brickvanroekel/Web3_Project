package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ContactsHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Role[] roles = {Role.administrator,Role.customer};
        Utility.checkrole(request, roles);

        Person person = (Person) request.getSession().getAttribute("person");
        ArrayList<String> errors = new ArrayList<>();

        if(person==null){
            errors.add("Log in");
            request.setAttribute("errors",errors);
            return "index.jsp";
        }
        request.setAttribute("datePreviousValue",LocalDate.now());
        request.setAttribute("hourPreviousValue",LocalTime.now());
        request.setAttribute("contacts",getDb().getContactsUser(person.getUserid()));
        return "contacts.jsp";
    }
}
