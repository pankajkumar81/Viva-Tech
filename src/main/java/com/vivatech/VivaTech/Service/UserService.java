package com.vivatech.VivaTech.Service;



import com.vivatech.VivaTech.Dto.PageableResponse;
import com.vivatech.VivaTech.Dto.UserDto;

import java.util.List;

public interface UserService
{
    //Create

    UserDto createUser(UserDto userDto);

    //Update
    UserDto updateUser(UserDto userDto,String useremail);

    //Delete

    void deleteUser(String userId);

    //Get all Users

    PageableResponse<UserDto> getAllUser();

    //Get Single User by ID

    UserDto getUserByEmail(String userEmail);


    //Search User

}
