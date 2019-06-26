package mk.finki.ukim.wp.studentsapi.repository;

import mk.finki.ukim.wp.studentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<List<Student>> findAllByStudyProgram(Long id);

}
