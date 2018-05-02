package com.nice.car.exception.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nice.car.dto.error.ErrorDetail;
import com.nice.car.exception.CarNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(CarNotFoundException rnfe, HttpServletRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Car Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

}
