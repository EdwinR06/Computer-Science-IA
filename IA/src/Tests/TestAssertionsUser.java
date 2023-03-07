package Tests;

import main.Student;
import org.junit.Test;

import main.User;
import main.Teacher;


import static org.junit.Assert.*;
public class TestAssertionsUser {
    @Test
    public void testAssertionsUser(){
        User student = new Student("student");
        User teacher = new Teacher("teacher", "password");

        String expectedStudentUsername = "student";
        String resultStudentUsername = student.getUsername();
        assertEquals(expectedStudentUsername, resultStudentUsername);

        student.setUsername("newStudent");
        String expectedStudentUsername1 = "newStudent";
        String resultStudentUsername1 = student.getUsername();
        assertEquals(expectedStudentUsername1, resultStudentUsername1);

        String expectedTeacherUsername = "teacher";
        String resultTeacherUsername = teacher.getUsername();
        assertEquals(expectedTeacherUsername, resultTeacherUsername);

        teacher.setUsername("newTeacher");
        String expectedTeacherUsername1 = "newTeacher";
        String resultTeacherUsername1 = teacher.getUsername();
        assertEquals(expectedTeacherUsername1, resultTeacherUsername1);

    }
}
