package ui.controller;

import domain.service.ContactTracingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ContactTracingService service = new ContactTracingService();

    HandlerFactory handFact = new HandlerFactory();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        String dest = "index.jsp";
        try {
            String succes = (String) request.getSession().getAttribute("succes");
            request.setAttribute("succes",succes);
            request.getSession().removeAttribute("succes");

            String action = request.getParameter("command");
            RequestHandler handler = handFact.getHandler(action, service);
            dest = handler.handleRequest(request,response);
            RequestDispatcher view = request.getRequestDispatcher(dest);
            view.forward(request,response);
        }catch (NotAuthorizedException e){
            request.setAttribute("errors","You have insufficient rights to have a look at the requested page.");
            dest = "index.jsp";
        } catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
            System.out.println(exception.getMessage());
            dest = "index.jsp";
        }
        if(!response.isCommitted()){
            request.getRequestDispatcher(dest).forward(request,response);
        }
    }



}
