package com.vivatech.VivaTech.Controller;


import com.vivatech.VivaTech.Dto.ApiResponseMessage;
import com.vivatech.VivaTech.Dto.PageableResponse;
import com.vivatech.VivaTech.Dto.UserDto;
import com.vivatech.VivaTech.Service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    //Create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto)
    {
        UserDto userDto1=userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") String userId,  @RequestBody UserDto userDto)
    {
        UserDto userDto1=userService.updateUser(userDto , userId);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    //Delete User
    @DeleteMapping("/{userEmail}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userEmail)
    {
        userService.deleteUser(userEmail);
        ApiResponseMessage message= ApiResponseMessage
                .builder()
                .message("User deleted Successfully!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(message , HttpStatus.OK);
    }

    //Get All
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUser()
    {
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }



    //Get by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
    {
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }

}


