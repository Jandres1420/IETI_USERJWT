package edu.eci.usermicro.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.usermicro.dto.UserDto;
import edu.eci.usermicro.entities.*;
import edu.eci.usermicro.service.UserService;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final AtomicLong counter = new AtomicLong();
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> allUsers = userService.getAll();
        return new ResponseEntity<List<UserDto>>(userService.fromEntityToDtos(allUsers), HttpStatus.ACCEPTED);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
        User user = userService.findById(id);
        return new ResponseEntity<UserDto>(userService.fromEntityToDto(user),HttpStatus.ACCEPTED);
    }


    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        User user = userService.fromDtoToEntity(userDto);
        userService.create(user);
        UserDto userDto1= userService.fromEntityToDto(user);
        System.out.println("User dto 1: " + userDto1.getPassword());
        return new ResponseEntity<UserDto>(userDto1,HttpStatus.ACCEPTED);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id ) {
        User userC = userService.fromDtoToEntity(user);
        userService.fromEntityToDto(userService.update(userC, id));
        return new ResponseEntity<UserDto>(userService.fromEntityToDto(userService.update(userC, id)),HttpStatus.ACCEPTED) ;
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping( "/{id}" )
    public boolean delete( @PathVariable String id ) {
        return userService.deleteById(id);
    }
}

