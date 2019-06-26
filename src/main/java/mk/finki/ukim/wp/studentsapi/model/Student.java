package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @Column(name="index")
    private String index;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="fk_program")
    private Long studyProgram;

    public Student(){}

    public Student(String index, String name, String lastName, Long studyProgram) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.studyProgram = studyProgram;
    }

    public Student(String index){
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(Long studyProgram) {
        this.studyProgram = studyProgram;
    }

    @Override
    public String toString() {
        return "Student{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName;
    }
}
