package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddContactHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        ArrayList<String> errors = new ArrayList<>();

        Contact contact = new Contact();
        setFirstName(contact,request,errors);
        setLastName(contact,request,errors);
        setDate(contact,request,errors);
        setHour(contact,request,errors);
        setGsm(contact,request,errors);
        setEmail(contact,request,errors);


        if (errors.size() == 0) {
            try {
                db.addContact(contact);
                return "contact.jsp";
            } catch (Exception exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors",errors);
        return "contact.jsp";
    }



    private void setFirstName(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String firstName= request.getParameter("firstName");
        try{
            contact.setFirstName(firstName);
            request.setAttribute("firstNameClass", "has-succes");
            request.setAttribute("firstNamePreviousValue", firstName);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("firstNameClass", "has-error");
        }
    }

    private void setLastName(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String lastName= request.getParameter("lastName");
        try{
            contact.setLastName(lastName);
            request.setAttribute("lastNameClass", "has-succes");
            request.setAttribute("lastNamePreviousValue", lastName);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("lastNameClass", "has-error");
        }
    }

    private void setDate(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String date = request.getParameter("date");
        try{
            contact.setDate(date);
            request.setAttribute("dateClass", "has-succes");
            request.setAttribute("datePreviousValue", date);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("dateClass", "has-error");
        }
    }

    private void setHour(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String hour = request.getParameter("hour");
        try{
            contact.setHour(hour);
            request.setAttribute("hourClass", "has-succes");
            request.setAttribute("hourPreviousValue", hour);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("hourClass", "has-error");
        }
    }

    private void setGsm(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String gsm= request.getParameter("gsm");
        try{
            contact.setGsm(gsm);
            request.setAttribute("gsmClass", "has-succes");
            request.setAttribute("gsmPreviousValue", gsm);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("gsmClass", "has-error");
        }
    }

    private void setEmail(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try{
            contact.setEmail(email);
            request.setAttribute("emailClass", "has-succes");
            request.setAttribute("emailPreviousValue", email);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("emailClass", "has-error");
        }
    }


}
