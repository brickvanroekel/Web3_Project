package ui.controller;

import domain.model.CovidTest;
import domain.model.DomainException;
import domain.model.Person;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestPositiveHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<String> errors = new ArrayList<>();

        CovidTest test = new CovidTest();
        setUserid(test,request,errors);
        setDate(test, request, errors);
        setPositive(test, request, errors);

        if(errors.size()==0){
            try {
                getDb().addTest(test);
                request.getSession().setAttribute("succes","You have succesfully registered a test!");
                response.sendRedirect("Servlet?command=Contacts");
                return "contacts.jsp";
            }catch (Exception e){
                e.printStackTrace();
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors",errors);
        return "registertest.jsp";
    }

    private void setUserid(CovidTest test, HttpServletRequest request, ArrayList<String> errors){
        String userid = ((Person) request.getSession().getAttribute("person")).getUserid();
        try {
            test.setUserid(userid);
        } catch (Exception e){
            errors.add(e.getMessage());
        }
    }


    private void setDate(CovidTest test, HttpServletRequest request, ArrayList<String> errors){
        String dateString = request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            request.setAttribute("datePreviousValue",dateString);
            Date date = Date.valueOf(dateString);
            test.setDate(date);
        } catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setPositive(CovidTest test, HttpServletRequest request, ArrayList<String> errors){
        try {
            test.setPositive(true);
        } catch (Exception e){
            errors.add(e.getMessage());
        }
    }

}
