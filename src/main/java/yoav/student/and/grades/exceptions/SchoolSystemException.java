package yoav.student.and.grades.exceptions;

public class SchoolSystemException extends Exception{
    public SchoolSystemException() {
        super("Student/Grade not found");
    }

    public SchoolSystemException(String message) {
        super(message);
    }
}
