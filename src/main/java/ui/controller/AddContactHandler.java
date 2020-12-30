package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddContactHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Person person = (Person) request.getSession().getAttribute("person");
        ArrayList<String> errors = new ArrayList<>();

        Contact contact = new Contact();
        setFirstName(contact,request,errors);
        setLastName(contact,request,errors);
        setTimestamp(request,contact,errors);
        setGsm(contact,request,errors);
        setEmail(contact,request,errors);
        contact.setContact(person.getUserid());

        if(person==null){
            request.setAttribute("errors","Log in");
            return "index.jsp";
        }

        if (errors.size() == 0) {
            try {
                db.addContact(contact);
                ArrayList<Contact> contacts = getDb().getContactsUser(person.getUserid());
                request.setAttribute("contacts",contacts);
                request.setAttribute("firstNamePreviousValue", "");
                request.setAttribute("lastNamePreviousValue", "");
                request.setAttribute("datePreviousValue", LocalDate.now());
                request.setAttribute("hourPreviousValue", LocalTime.now());
                request.setAttribute("gsmPreviousValue", "");
                request.setAttribute("emailPreviousValue", "");
                request.getSession().setAttribute("succes","You have succesfully added a contact!");
                response.sendRedirect("Servlet?command=Contacts");
                return "contacts.jsp";
            } catch (Exception exc) {
                exc.printStackTrace();
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors",errors);
        return "contacts.jsp";
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

    private void setTimestamp(HttpServletRequest request, Contact contact, ArrayList<String> errors){
        String date = request.getParameter("date");
        String hour=request.getParameter("hour");
        String timestampPattern="yyyy-M-d HH:mm";
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(timestampPattern);
        LocalDateTime dateTime=null;
        Timestamp timestamp=null;
        try{
            request.setAttribute("hourPreviousValue",hour);
            request.setAttribute("datePreviousValue",date);
            dateTime=LocalDateTime.from(formatter.parse(date+" "+hour));
            timestamp=Timestamp.valueOf(dateTime);
        }catch(DateTimeException e){
            errors.add("date or hour invalid");
        }
        contact.setTimestamp(timestamp);
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
