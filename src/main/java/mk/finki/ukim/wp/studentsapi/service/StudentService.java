package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudentInput;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentInput> getAllStudents();

    Optional<Student> getStudentById(String id);

    void deleteStudentById(String id);

    boolean addStudent(String index, String name, String lastName, String studyProgram);

    void modifyStudent(Student modified);

    List<Student> findAllByStudyProgram(Long id);

}
