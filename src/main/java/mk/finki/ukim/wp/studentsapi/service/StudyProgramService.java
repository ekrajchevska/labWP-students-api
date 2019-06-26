package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;

import java.util.List;

public interface StudyProgramService {

    List<StudyProgram> getAllStudyPrograms();

    void addStudyProgram(String name);

    StudyProgram getStudyProgramByName(String studyProgramName);

    void deleteStudyProgram(Long id);

    void updateStudyProgram(StudyProgram studyProgram);

}
