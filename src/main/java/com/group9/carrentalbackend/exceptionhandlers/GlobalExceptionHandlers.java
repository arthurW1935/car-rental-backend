package com.group9.carrentalbackend.exceptionhandlers;

import com.group9.carrentalbackend.dtos.ExceptionDto;
import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithMaticException(){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("Something went wrong");
        dto.setResolution("Arithmetic Exception");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundsException(){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("Something went wrong");
        dto.setResolution("Array Index Out Of Bounds Exception");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleBranchNotFoundException(BranchNotFoundException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(e.getMessage());
        dto.setResolution("Branch Not Found");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
