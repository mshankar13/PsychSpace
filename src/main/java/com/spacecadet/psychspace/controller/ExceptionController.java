package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.exception.SourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aliao on 3/24/2017.
 */
@Controller
public class ExceptionController {
    /**
     * handle error 404
     * @param request
     * @param e
     * @return
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
