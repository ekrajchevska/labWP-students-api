package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.repository.StudentRepository;
import mk.finki.ukim.wp.studentsapi.repository.StudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    public StudyProgramRepository studyProgramRepository;

    public StudyProgramServiceImpl(){}

    @Autowired
    public StudyProgramServiceImpl(StudyProgramRepository studyProgramRepository) {
        this.studyProgramRepository = studyProgramRepository;
    }

    public StudyProgram getStudyProgramById(Long id){
        Optional<StudyProgram> sp = this.studyProgramRepository.findById(id);
        if(sp.isPresent())
            return sp.get();
        return null;
    }

    public StudyProgram getStudyProgramByName(String studyName){
        Optional<StudyProgram> sp = this.studyProgramRepository.findByName(studyName);
        if(sp.isPresent()) return sp.get();
        return null;
    }

    public List<StudyProgram> getAllStudyPrograms(){
        return this.studyProgramRepository.findAll();
    }

    public void addStudyProgram(String name){
        Optional<StudyProgram> exists = this.studyProgramRepository.findByName(name);
        if(exists.isPresent()) return;
        this.studyProgramRepository.save(new StudyProgram(name));
    }

    public void deleteStudyProgram(Long id){
       this.studyProgramRepository.deleteById(id);
    }

    public void updateStudyProgram(StudyProgram studyProgram){
       this.studyProgramRepository.save(studyProgram);
    }


}
