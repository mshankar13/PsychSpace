package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.exception.SourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for catching exceptions for 404 page not found.
 * use cases: routing for custom 404 error page
 * Created by aliao on 3/24/2017.
 */
@Controller
public class ExceptionController {
    /**
     * handle error 404
     * @param request http request
     * @param e exception caught
     * @return 404 page
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SourceNotFoundException.class)
    public String handleSourceNotFoundException(HttpServletRequest request, Exception e) {
        return "404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(){
        throw new SourceNotFoundException();
    }

//    @RequestMapping(value = "/{anything}", method = RequestMethod.GET)
//    public String error(){
//        return "404";
    //}
}
