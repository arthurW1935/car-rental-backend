package com.group9.carrentalbackend.exceptionhandlers;

import com.group9.carrentalbackend.dtos.ExceptionDto;
import com.group9.carrentalbackend.exceptions.*;
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

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleCustomerNotFoundException(CustomerNotFoundException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(e.getMessage());
        dto.setResolution("Customer id is wrong");
        return new ResponseEntity<>(dto , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEmployeeNotFoundException(EmployeeNotFoundException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(e.getMessage());
        dto.setResolution("Employee Not Found");
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ExceptionDto> InavlidArgumentTypeException(InvalidArgumentException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(e.getMessage());
        dto.setResolution("Invalid Argument Type");
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleEmployeeAlreadyExistException(EmployeeAlreadyExistsException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(e.getMessage());
        dto.setResolution("Employee Already Exists");
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidVehicleTypeException.class)
    public ResponseEntity<ExceptionDto> handleInvalidVehicleTypeException(InvalidVehicleTypeException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(e.getMessage());
        dto.setResolution("Invalid Vehicle Type");
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleVehicleNotFoundException(VehicleNotFoundException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(e.getMessage());
        dto.setResolution("Vehicle Not Found");
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }

}
