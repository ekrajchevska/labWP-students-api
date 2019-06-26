package mk.finki.ukim.wp.studentsapi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class StudentsApiIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void givenIndexWhenGettingStudentThenReturnStudent(){
        given()
        .when()
                .get("/api/students/151050")
        .then()
                .statusCode(200)
                .body("name", equalTo("Evgenija"));
    }

    @Test
    public void returnListOfAllStudyPrograms(){
        given()
        .when()
                .get("/api/study_programs")
        .then()
                .body("size()", greaterThan(3))
                .body("find { it.name == 'KNI'}",hasKey("id"));
    }

    @Test
    public void createNewStudentShouldReturnCreated(){
        Map<String,String> newStudent = new HashMap<>();
        newStudent.put("index","155021");
        newStudent.put("name","Marko");
        newStudent.put("lastName","Markovski");
        newStudent.put("studyProgram","MT");

        given()
                .contentType(ContentType.JSON)
                .body(newStudent)
        .when()
                .post("/api/students")
        .then()
                .statusCode(201);
    }

    @Test
    public void modifyStudyProgramShouldReturnModified(){
        Map<String,String> requestBody = new HashMap<>();
        requestBody.put("name","IKI");
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .patch("/api/study_programs/17")
        .then()
                .statusCode(200)
                .body("id", equalTo(17))
                .and()
                .body("name",equalTo("IKI"));
    }

    @Test
    public void deletingStudentShouldReturnDeleted(){
       HashMap map = given()
        .when()
                .delete("/api/students/152096")
        .then()
                .statusCode(200)
                .and()
                .extract()
                .as(HashMap.class);

        given()
        .when()
                .get("/api/study_programs/"+map.get("studyProgram"))
        .then()
                .body("name",equalTo("MT"));
    }
}
