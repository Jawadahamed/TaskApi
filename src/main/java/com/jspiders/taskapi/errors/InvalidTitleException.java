package com.jspiders.taskapi.errors;

public class InvalidTitleException extends RuntimeException{
   public InvalidTitleException(String message){
       super(message);
   }
}
