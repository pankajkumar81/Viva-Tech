package com.vivatech.VivaTech.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {
    
    private String email;
    private String password;
}
