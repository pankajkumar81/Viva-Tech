package com.vivatech.VivaTech.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Size(min = 2 , max = 50, message = "Invalid Name !!")
    private String name;
    @Id
    @NotBlank(message = "Email is required!!")
    //@Email(message = "Invalid Email !!",)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Invalid Email !!")
    private String email;
    @Size(min = 4,message = "Password is min 4 character and max 8 !!")
    @NotBlank(message = "Password is required!!")
    private String password;
    @Size(min = 4, max = 6 , message = "Invalid Gender !!")
    private String gender;

}