package Tests;


import org.junit.Test;

import main.Teacher;


import static org.junit.Assert.*;
public class TestAssertionsTeacher {
    @Test
    public void testAssertionsUser(){
        Teacher teacher = new Teacher("teacher", "password");

        String expectedTeacherPassword = "password";
        String resultTeacherPassword = teacher.getPassword();
        assertEquals(expectedTeacherPassword, resultTeacherPassword);

        teacher.setPassword("newPassword");
        String expectedTeacherPassword1 = "newPassword";
        String resultTeacherPassword1 = teacher.getPassword();
        assertEquals(expectedTeacherPassword1, resultTeacherPassword1);

    }
}
