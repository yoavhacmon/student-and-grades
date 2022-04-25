package yoav.student.and.grades.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoav.student.and.grades.beans.Student;
import yoav.student.and.grades.exceptions.SchoolSystemException;
import yoav.student.and.grades.service.StudentService;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    //create
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    //read
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<?> getStudent( @PathVariable long id) throws SchoolSystemException {
        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?>getStudentsByName(@PathVariable String name) throws SchoolSystemException {
        return new ResponseEntity<>(studentService.getListByName(name),HttpStatus.OK);
    }

    @GetMapping("/getAvg/{id}")
    public ResponseEntity<?> getStudentAng(@PathVariable long id) throws SchoolSystemException {
        return new ResponseEntity<>(studentService.getStudentAvg(id),HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable long id) throws SchoolSystemException {
        studentService.deleteStudent(id);
    }
}
