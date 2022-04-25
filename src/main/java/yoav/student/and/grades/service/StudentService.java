package yoav.student.and.grades.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoav.student.and.grades.beans.Student;
import yoav.student.and.grades.exceptions.SchoolSystemException;
import yoav.student.and.grades.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    //add student
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    //delete student
    public void deleteStudent(long id) throws SchoolSystemException {
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }else {
            throw new SchoolSystemException("Id Not Found");
        }
    }
    //get all students
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    //get single student
    public Student getStudentById(long id) throws SchoolSystemException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new SchoolSystemException("Id Not Found");
        }
    }
    //get list by name
    public List<Student> getListByName(String name) throws SchoolSystemException {
        List<Student> studentList = studentRepository.findByName(name);
        if (studentList.isEmpty()){
            throw new SchoolSystemException("no student with this name");
        } else {
            return studentList;
        }

    }
    //find avg by student
    public double getStudentAvg(long id) throws SchoolSystemException {
        if (studentRepository.existsById(id)){
            return studentRepository.findAvgByStudentId(id);
        } else {
            throw new SchoolSystemException("id Not Found");
        }
    }
}
