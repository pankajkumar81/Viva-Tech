package com.vivatech.VivaTech.Configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    //Use for conversion entity->dto or dto->entity
    @Bean
    public ModelMapper mapper()
    {
        return new ModelMapper();
    }
}
