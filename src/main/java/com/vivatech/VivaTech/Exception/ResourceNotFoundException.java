package com.vivatech.VivaTech.Exception;


import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException()
    {
        super("Resource not found exception !!");
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }


}

