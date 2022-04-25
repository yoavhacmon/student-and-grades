package yoav.student.and.grades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yoav.student.and.grades.beans.Student;

import java.sql.Date;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByName(String name);

    @Query(value = "SELECT AVG (score) FROM `student-and-grades`.grade WHERE id=any (SELECT grades_id FROM `student-and-grades`.student_grades WHERE student_id = ?1)", nativeQuery = true)
    double findAvgByStudentId(long student_id);
}
