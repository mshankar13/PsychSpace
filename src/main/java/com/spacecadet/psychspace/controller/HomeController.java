package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.requests.AuthenticateUserRequest;
import com.spacecadet.psychspace.requests.RegisterUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by aliao on 3/20/2017.
 */
@Controller
public class HomeController {

    private UserManager userManager = new UserManager();

    /**
     * all visit to home page
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        model.setViewName("home");

        return model;
    }

    /**
     * after login goto home page
     * @param request
     * @return
     */
    @RequestMapping(value = "/home/1", method = RequestMethod.POST)
    public ResponseEntity<?> afterLogin(AuthenticateUserRequest request){
        if (userManager.verifyUser(request.getEmail(), request.getPassword())){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * after register goto home page
     * @param request
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ResponseEntity<?> afterRegister(RegisterUserRequest request){
        if(userManager.addUser(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(),
                request.getDoB(), "User")) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
