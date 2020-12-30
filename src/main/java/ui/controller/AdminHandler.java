package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminHandler extends RequestHandler{
    private LocalDate startDate;
    private LocalDate endDate;
    private String userid;
    ArrayList<String> errors = new ArrayList<>();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, ServletException {
        Person person = (Person) request.getSession().getAttribute("person");
        Role[] roles = {Role.administrator};
        Utility.checkrole(request, roles);

        if("POST".equalsIgnoreCase(request.getMethod())){
            List<Contact> filteredContacts = new ArrayList<>();
            setStartDate(request);
            setEndDate(request);
            setUser(request);

            System.out.println("start: "+startDate);
            System.out.println("end: "+endDate);
            System.out.println("user: "+userid);
            List<Contact> contacts = getDb().getContactsUser(userid);
            try {
                for (Contact c:contacts) {
                    if((c.getTimestamp().toLocalDateTime().toLocalDate().isAfter(startDate) || c.getTimestamp().toLocalDateTime().toLocalDate().isEqual(startDate)) && (c.getTimestamp().toLocalDateTime().toLocalDate().isBefore(endDate)) || c.getTimestamp().toLocalDateTime().toLocalDate().isEqual(endDate)){
                        filteredContacts.add(c);
                    }
                }
                request.setAttribute("startPreviousValue",startDate);
                request.setAttribute("endPreviousValue",endDate);
                request.setAttribute("contacts",filteredContacts);
                return "admin.jsp";
            } catch (Exception exc) {
                errors.add(exc.getMessage());
            }
        }

        request.setAttribute("db",db.getPersons());
        request.setAttribute("tests",db.getAllTests());
        request.setAttribute("contacts",getDb().getAllContacts());
        return "admin.jsp";
    }

    private void setStartDate(HttpServletRequest request){
        String startString=request.getParameter("startDate");
        this.startDate = LocalDate.parse(startString);

    }

    private void setEndDate(HttpServletRequest request){
        String endString=request.getParameter("endDate");
        this.endDate =LocalDate.parse(endString);
    }

    private void setUser(HttpServletRequest request){
        String user = request.getParameter("user");
        this.userid = user;
    }
}
