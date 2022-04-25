package yoav.student.and.grades.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import yoav.student.and.grades.exceptions.SchoolSystemException;

@RestController
@ControllerAdvice
public class StudentAdvice {
    @ExceptionHandler(value = {SchoolSystemException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionDetails handleException(Exception exception){
        return new ExceptionDetails("error", exception.getMessage());
    }
}
