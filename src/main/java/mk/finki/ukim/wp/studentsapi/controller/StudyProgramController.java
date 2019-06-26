package mk.finki.ukim.wp.studentsapi.controller;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.service.impl.StudyProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin({"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class StudyProgramController {

    private StudyProgramServiceImpl studyProgramServiceImpl;

    public StudyProgramController(){}

    @Autowired
    public StudyProgramController(StudyProgramServiceImpl studyProgramServiceImpl) {
        this.studyProgramServiceImpl = studyProgramServiceImpl;
    }

    @GetMapping("/study_programs/{id}")
    public ResponseEntity<StudyProgram> getStudyProgram(@PathVariable Long id){
        StudyProgram studyProgram = this.studyProgramServiceImpl.getStudyProgramById(id);
        if(studyProgram == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(studyProgram,HttpStatus.OK);
    }

    @PatchMapping("/study_programs/{id}")
    public ResponseEntity<StudyProgram> modifyStudyProgram(@PathVariable Long id,
                                                           @RequestBody StudyProgram studyProgram){
        StudyProgram sp = this.studyProgramServiceImpl.getStudyProgramById(id);
        if(sp==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(studyProgram.getName()!=null)
            sp.setName(studyProgram.getName());

        this.studyProgramServiceImpl.updateStudyProgram(sp);
        return new ResponseEntity<>(sp,HttpStatus.OK);
    }

    // 7)
    @GetMapping("/study_programs")
    public ResponseEntity<List<StudyProgram>> getStudyPrograms(){
        List<StudyProgram> studyPrograms = this.studyProgramServiceImpl.getAllStudyPrograms();
        return new ResponseEntity<>(studyPrograms, HttpStatus.OK);
    }

    // 8)
    @PostMapping("/study_programs")
    public void addStudyProgram(@RequestBody StudyProgram studyProgram,
                                HttpServletResponse response){
        response.setStatus(201);
        this.studyProgramServiceImpl.addStudyProgram(studyProgram.getName());
    }

    // 9)
    @DeleteMapping("/study_programs/{id}")
    public void deleteStudyProgram(@PathVariable Long id){

        this.studyProgramServiceImpl.deleteStudyProgram(id);
    }


}
