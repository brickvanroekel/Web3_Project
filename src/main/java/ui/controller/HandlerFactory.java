package ui.controller;


import domain.model.ContactTracingService;
import domain.model.DomainException;

import java.lang.reflect.InvocationTargetException;

public class HandlerFactory {

    public RequestHandler getHandler(String command, ContactTracingService service) throws DomainException {
        RequestHandler handler = null;

        try {
            Class handlerClass = Class.forName("ui.controller."+command+"Handler");
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setDb(service);
        }catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException exception){
            throw new RuntimeException(exception.getMessage());
        }
        return handler;
    }
}
