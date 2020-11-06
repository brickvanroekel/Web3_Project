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
        Person person = null;
        List<String> errors = new ArrayList<>();

        try {
            person = getDb().getPerson(userid);
        } catch (Exception exception) {
            errors.add(exception.getMessage());
            request.setAttribute("errors", errors);
            return "index.jsp";
        }

        String password = request.getParameter("password");
        boolean login = false;

        if (person != null && !password.isEmpty()) {
            login = person.isCorrectPassword(password);
            System.out.println("Logged in as " + person.toString());
        }
        if (person != null && login) {
            request.getSession().setAttribute("person", person);
        } else {
            request.setAttribute("errors", "Password Incorrect");
        }
        if (!userid.isEmpty() && person == null) {
            request.setAttribute("errors", "Username not found");
        }

        return "index.jsp";
    }

    private void setSession(HttpServletRequest request, Person person) {
        HttpSession session = request.getSession();

        session.setAttribute("person", person);

        request.setAttribute("person", person);
    }
}


