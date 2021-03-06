package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.InternalServerError;
import com.example.demo.exception.WrongParameters;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(name = "/readalluser",value="", method=RequestMethod.GET, produces="application/json" )
    public ResponseEntity<List<UserDTO>> readAll() {
        List<UserDTO> user = service.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(name ="/fetchuser", value="{username}", method=RequestMethod.GET, produces="application/json" )
    public ResponseEntity<UserDTO> read(@PathVariable String username) {
        UserDTO user = service.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(name="/createuser", value="", method=RequestMethod.POST, produces="application/json" )
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
        try {
            service.createUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (WrongParameters p) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (InternalServerError u) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(name ="/updateuser", value="", method=RequestMethod.PUT, produces="application/json" )
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user) {
        try {
            service.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (WrongParameters p) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (InternalServerError u) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="{username}", method=RequestMethod.DELETE, produces="application/json" )
    public ResponseEntity<UserDTO> delete(@PathVariable String username) {
        try {
            service.deleteUser(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (WrongParameters p) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (InternalServerError u) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
