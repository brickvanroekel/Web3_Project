package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutHandler extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession().removeAttribute("person");
        request.getSession().setAttribute("succes","You have succesfully logged out!");
        return "index.jsp";
    }
}
