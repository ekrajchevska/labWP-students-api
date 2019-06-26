package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudentInput;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.ParameterMissingException;
import mk.finki.ukim.wp.studentsapi.repository.StudentRepository;
import mk.finki.ukim.wp.studentsapi.repository.StudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    public StudentRepository studentRepository;

    public StudyProgramRepository studyProgramRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudyProgramRepository studyProgramRepository){
        this.studentRepository = studentRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    public List<StudentInput> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        List<StudentInput> studentInputs = new ArrayList<>(students.size());
        for(int i=0;i<students.size();i++) {
            Optional<StudyProgram> sp = this.studyProgramRepository.findById(students.get(i).getStudyProgram());
            if(sp.isPresent()) {
                studentInputs.add(new StudentInput(
                        students.get(i).getIndex(),
                        students.get(i).getName(),
                        students.get(i).getLastName(),
                        sp.get().getName()));
            }
        }
        return studentInputs;
    }

    public Optional<Student> getStudentById(String id){
        return this.studentRepository.findById(id);
    }

    public void deleteStudentById(String id){
        this.studentRepository.deleteById(id);
    }

    public boolean addStudent(String index, String name, String lastName, String studyProgram) {

        if(this.studentRepository.findById(index).isPresent()) return false;

        if(index==null || name==null || lastName==null || studyProgram==null){
            //throw new ParameterMissingException();
            return false;
        }

        if(!(index.length()==6) || !index.matches("[0-9]+")) {
           //throw new InvalidIndexFormatException();
            return false;
        }

        Optional<StudyProgram> sp = this.studyProgramRepository.findByName(studyProgram);
        if(!sp.isPresent()) {
            return false;
            //throw new StudyProgramNotFoundException();
        }

        this.studentRepository.save(new Student(index,name,lastName,sp.get().getId()));
        return true;

    }

    public void modifyStudent(Student modified){
        Optional<Student> s = this.studentRepository.findById(modified.getIndex());
        if(s.isPresent()){
            Student student = s.get();
            if(modified.getName()==null)
                modified.setName(student.getName());
            if(modified.getLastName()==null)
                modified.setLastName(student.getLastName());
            if(modified.getStudyProgram()==null)
                modified.setStudyProgram(student.getStudyProgram());

            this.studentRepository.save(modified);
        }
    }

    public List<Student> findAllByStudyProgram(Long id){
      if(this.studentRepository.findAllByStudyProgram(id).isPresent())
          return this.studentRepository.findAllByStudyProgram(id).get();
      return null;
    }

    public Optional<StudyProgram> findByName(String nameStudyProgram){
        return this.studyProgramRepository.findByName(nameStudyProgram);

    }




}
