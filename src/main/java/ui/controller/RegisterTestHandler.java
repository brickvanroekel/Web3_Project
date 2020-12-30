package ui.controller;

import domain.model.CovidTest;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

public class RegisterTestHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Person person = (Person) request.getSession().getAttribute("person");

        try {
            CovidTest test = getDb().getTestUser(person.getUserid());
            System.out.println(test.getUserid());
            Date testDate = test.getDate();

            Date today = Date.valueOf(LocalDate.now());

            if(findTimeDifference(testDate,today)>= 14){
                getDb().removeTest(person.getUserid());
                System.out.println("Test deleted");
            }
            return "existingtest.jsp";

        }catch (Exception exception){
            return "registertest.jsp";
        }

    }

    //Found on GeeksForGeeks.org
    private long findTimeDifference(Date date1, Date date2){

        long difference_In_Time = date2.getTime() - date1.getTime();
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        return difference_In_Days;
    }
}
