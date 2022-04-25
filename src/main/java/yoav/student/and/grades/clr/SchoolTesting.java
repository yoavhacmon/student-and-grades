package yoav.student.and.grades.clr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import yoav.student.and.grades.beans.Grade;
import yoav.student.and.grades.beans.Student;
import yoav.student.and.grades.beans.Topic;
import yoav.student.and.grades.repositories.StudentRepository;
import yoav.student.and.grades.util.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SchoolTesting implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        Grade grade1 = Grade.builder()
                .score(100)
                .topic(Topic.MATH)
                .build();
        Grade grade2 = Grade.builder()
                .score(95)
                .topic(Topic.ENGLISH)
                .build();
        Grade grade3 = Grade.builder()
                .score(98)
                .topic(Topic.PHYSICS)
                .build();
        Grade grade4 = Grade.builder()
                .score(90)
                .topic(Topic.MATH)
                .build();
        Grade grade5 = Grade.builder()
                .score(100)
                .topic(Topic.ENGLISH)
                .build();
        Grade grade6 = Grade.builder()
                .score(85)
                .topic(Topic.PHYSICS)
                .build();

        Student student1 = Student.builder()
                .birthday(Date.valueOf(LocalDate.now().plusDays(250)))
                .name("Yoav")
                .isActive(true)
                .grades(List.of(grade1, grade2, grade3))
                .build();
        Student student2 = Student.builder()
                .birthday(Date.valueOf(LocalDate.now().plusDays(125)))
                .name("Michal")
                .isActive(true)
                .grades(List.of(grade4, grade5, grade6))
                .build();

        //add students
        restTemplate.postForEntity("http://localhost:8080/students/add",student1,Student.class);
        restTemplate.postForEntity("http://localhost:8080/students/add",student2,Student.class);
        //read all
        System.out.println("all students:");
        List<Student> studentList = restTemplate.getForObject("http://localhost:8080/students/getAll", List.class);
        System.out.println(studentList);
        System.out.println("===============================");
        //read one student
        try {
            System.out.println("one student:");
            Student student = restTemplate.getForObject("http://localhost:8080/students/getStudent/2", Student.class);
            TablePrinter.print(student);
            System.out.println("===============================");
        }catch (Exception err){
            System.out.println(err.getMessage());
        }

        //by name
        try{
        System.out.println("by name:");
        List<Student> studentListByNane = restTemplate.getForObject("http://localhost:8080/students//getByName/Yoav", List.class);
        System.out.println(studentListByNane);
        System.out.println("===============================");
        }catch (Exception err){
            System.out.println(err.getMessage());
        }

        //get avg
        try {
        System.out.println("avg:");
        double avg = restTemplate.getForObject("http://localhost:8080/students/getAvg/1", Double.class);
        System.out.println(avg);
        }catch (Exception err){
            System.out.println(err.getMessage());
        }

        //delete
        try{
        restTemplate.delete("http://localhost:8080/students/delete/2");
        }catch (Exception err){
            System.out.println(err.getMessage());
        }

    }
}
