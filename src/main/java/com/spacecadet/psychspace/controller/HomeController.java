package com.spacecadet.psychspace.controller;

import com.spacecadet.psychspace.dataManager.UserManager;
import com.spacecadet.psychspace.utilities.AuthenticateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by aliao on 3/20/2017.
 */
@Controller
public class HomeController {

    private UserManager userManager = new UserManager();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        model.setViewName("home");

        return model;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ResponseEntity<?> afterLogin(@RequestBody AuthenticateUserRequest request){
        if (userManager.verifyUser(request.getUsername(), request.getPassword())){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ResponseEntity<?> afterRegister(@RequestBody AuthenticateUserRequest request){
        if (userManager.verifyUser(request.getUsername(), request.getPassword())){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
