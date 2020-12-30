package ui.controller;

import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static void checkrole(HttpServletRequest request, Role[] roles) throws NotAuthorizedException {
        Boolean authorized = false;
        Person currentUser = (Person) request.getSession().getAttribute("person");

        if (currentUser == null){
            for (Role role : roles) {
                if (role.equals(Role.guest)) authorized = true;
            }
        }
        else{
            for (Role role : roles) {
                if (role.equals(currentUser.getRole())) authorized = true;
            }
        }
        if (!authorized){
            System.out.println("Unauthorized page requested");
            throw new NotAuthorizedException();
        }
    }

}
