package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LogInHandler extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String userid = request.getParameter("userid");
        Person person;

        List<String> errors = new ArrayList<>();

        try {
            person = getDb().getPerson(userid);
        } catch (Exception exception) {
            errors.add(exception.getMessage());
            request.setAttribute("errors", errors);
            return "index.jsp";
        }

        try{
            String password = request.getParameter("password");
            boolean login = false;
            if(userid.trim().equals("")){
                request.setAttribute("errors", "No userid given");
                return "index.jsp";
            }
            if(password.trim().equals("")){
                request.setAttribute("errors", "No password given");
                return "index.jsp";
            }
            if (!userid.isEmpty() && person == null) {
                request.setAttribute("errors", "Username not found");
                return "index.jsp";
            }
            if (person != null && !password.isEmpty()) {
                login = person.isCorrectPassword(password);
                System.out.println("Logged in as " + person.toString());
            }
            if (person != null && login) {
                setSession(request,person);
            }
            else {
                request.setAttribute("errors", "Password incorrect");
                return "index.jsp";
            }
            request.getSession().setAttribute("succes","You have succesfully logged in!");
            response.sendRedirect("Servlet?command=Home");
            return "index.jsp";
        }catch (Exception e){
            errors.add(e.getMessage());
            request.setAttribute("errors",errors);
            return "index.jsp";
        }

    }

    private void setSession(HttpServletRequest request, Person person) {
        HttpSession session = request.getSession();

        session.setAttribute("person", person);

        request.setAttribute("person", person);
    }
}


