package dev.festus.blog.exception;


import java.util.Date;

public record ErrorDetails(
         Date timeStamp,
         String message,
         String details,
         int statusCode) {

}