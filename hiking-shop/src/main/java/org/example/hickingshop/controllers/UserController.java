package org.example.hickingshop.controllers;

import jakarta.validation.Valid;
import org.example.hickingshop.controllers.utils.ControllerUtils;
import org.example.hickingshop.dto.UserDto;
import org.example.hickingshop.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ControllerUtils controllerUtils;

    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    public ResponseEntity<?> ping(){

        return new ResponseEntity<>("PONG", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        try {
            logger.debug(userDto.toString());

            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(controllerUtils.getErrors(bindingResult));
            }

            return ResponseEntity.ok(userService.createUser(userDto));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }



}
