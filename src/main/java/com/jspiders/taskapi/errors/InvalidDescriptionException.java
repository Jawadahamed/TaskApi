package com.jspiders.taskapi.errors;

public class InvalidDescriptionException extends RuntimeException{
   public InvalidDescriptionException(String message){
       super(message);
   }
}
