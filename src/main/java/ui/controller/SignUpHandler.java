package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import static domain.model.Role.administrator;

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
                request.getSession().setAttribute("succes","You have succesfully registered!");
                response.sendRedirect("Servlet?command=Home");
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
            if(userid.equals("admin"))
                person.setRole(administrator);
            request.setAttribute("useridPreviousValue", userid);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String firstName= request.getParameter("firstName");
        try{
            person.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String lastName= request.getParameter("lastName");
        try{
            person.setLastName(lastName);
            request.setAttribute("lastNamePreviousValue", lastName);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try{
            person.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String password= request.getParameter("password");
        try{
            person.setPassword(password);
            request.setAttribute("passwordPreviousValue", password);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
        }
    }
}
