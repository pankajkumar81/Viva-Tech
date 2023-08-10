package com.vivatech.VivaTech.Service.ImplService;

import com.vivatech.VivaTech.Dto.PageableResponse;
import com.vivatech.VivaTech.Dto.UserDto;
import com.vivatech.VivaTech.Entity.User;
import com.vivatech.VivaTech.Exception.ResourceNotFoundException;
import com.vivatech.VivaTech.Helper.Helper;
import com.vivatech.VivaTech.Repository.UserRepository;
import com.vivatech.VivaTech.Service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {

        //encoding password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // UserDto -> Entity
        User user=dtoToEntity(userDto);
        User saveUser=userRepository.save(user);

        // Entity -> UserDto
        UserDto newDto=entityToDto(saveUser);

        return newDto;
    }

    //User update
    @Override
    public UserDto updateUser(UserDto userDto, String useremail) {

        //encoding password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user=userRepository.findById(useremail).orElseThrow(() -> new ResourceNotFoundException("User not found exception"));

        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());

        User updatedUser= userRepository.save(user);
        UserDto updatedDto=entityToDto(updatedUser);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userEmail) {

        User user=userRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found exception"));

        //delete user
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser() {


        //Get all User from the List
        List<User> user=userRepository.findAll();
        PageableResponse<UserDto> response= Helper.getPageableResponse(user,UserDto.class);

        return response;
    }

    @Override
    public UserDto getUserByEmail(String userEmail) {

        //Get single user from the List by Email
        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new ResourceNotFoundException("User not found from Email exception"));

        return entityToDto(user);
    }

    private UserDto entityToDto(User saveUser) {

        return mapper.map(saveUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {

        return mapper.map(userDto,User.class);
    }
}

