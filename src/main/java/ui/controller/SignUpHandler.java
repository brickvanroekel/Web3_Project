package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SignUpHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        ArrayList<String> errors = new ArrayList<>();

        Person person = new Person();
        setUserid(person, request,errors);
        setFirstName(person,request,errors);
        setLastName(person,request,errors);
        setEmail(person,request,errors);
        setPassword(person,request,errors);


        if (errors.size() == 0) {
            try {
                db.addPerson(person);
                return "index.jsp";
            } catch (Exception exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors",errors);
        return "register.jsp";
    }

    private void setUserid(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String userid= request.getParameter("userid").toLowerCase();
        try{
            person.setUserid(userid);
            request.setAttribute("useridClass", "has-succes");
            request.setAttribute("useridPreviousValue", userid);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("useridClass", "has-error");
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String firstName= request.getParameter("firstName");
        try{
            person.setFirstName(firstName);
            request.setAttribute("firstNameClass", "has-succes");
            request.setAttribute("firstNamePreviousValue", firstName);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("firstNameClass", "has-error");
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String lastName= request.getParameter("lastName");
        try{
            person.setLastName(lastName);
            request.setAttribute("lastNameClass", "has-succes");
            request.setAttribute("lastNamePreviousValue", lastName);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("lastNameClass", "has-error");
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try{
            person.setEmail(email);
            request.setAttribute("emailClass", "has-succes");
            request.setAttribute("emailPreviousValue", email);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("emailClass", "has-error");
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String password= request.getParameter("password");
        try{
            person.setPassword(password);
            request.setAttribute("passwordClass", "has-succes");
            request.setAttribute("passwordPreviousValue", password);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("passwordClass", "has-error");
        }
    }
}
