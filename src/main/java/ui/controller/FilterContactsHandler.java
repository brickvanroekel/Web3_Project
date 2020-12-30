package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilterContactsHandler extends RequestHandler {
    private LocalDate startDate;
    private LocalDate endDate;
    ArrayList<String> errors = new ArrayList<>();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Person person = (Person) request.getSession().getAttribute("person");

        if(request.getParameter("ClearFilter") != null){
            request.setAttribute("contacts",getDb().getContactsUser(person.getUserid()));
            return "contacts.jsp";
        }

        List<Contact> filteredContacts = new ArrayList<>();

        if(errors.size()==0){
            setStartDate(request);
            setEndDate(request);
            List<Contact> contacts = getDb().getContactsUser(person.getUserid());
            try {
                for (Contact c:contacts) {
                    if((c.getTimestamp().toLocalDateTime().toLocalDate().isAfter(startDate) || c.getTimestamp().toLocalDateTime().toLocalDate().isEqual(startDate)) && (c.getTimestamp().toLocalDateTime().toLocalDate().isBefore(endDate)) || c.getTimestamp().toLocalDateTime().toLocalDate().isEqual(endDate)){
                        filteredContacts.add(c);
                    }
                }
                request.setAttribute("startPreviousValue",startDate);
                request.setAttribute("endPreviousValue",endDate);
                request.setAttribute("contacts",filteredContacts);
                return "contacts.jsp";
            } catch (Exception exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors",errors);
        return "contacts.jsp";
    }

    private void setStartDate(HttpServletRequest request){
        String startString=request.getParameter("startDate");
        this.startDate =LocalDate.parse(startString);
    }

    private void setEndDate(HttpServletRequest request){
        String endString=request.getParameter("endDate");
        this.endDate =LocalDate.parse(endString);
    }

}
