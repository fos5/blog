package dev.festus.blog.exception.customExceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationException extends Exception{

    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException( int statusCode, String message){
    }
}