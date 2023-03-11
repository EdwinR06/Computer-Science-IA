package Tests;

import main.Library;
import main.Student;
import main.User;
import org.junit.Test;


import static org.junit.Assert.*;
public class TestAssertionsLibrary {
    @Test
    public void testAssertionsLibrary(){
        String[] dirs = {"/Users/edwinryerson/Documents/GitHub/Computer-Science-IA/IA/src/main/books.csv", "/Users/edwinryerson/Documents/GitHub/Computer-Science-IA/IA/src/main/teacher.csv", "/Users/edwinryerson/Documents/GitHub/Computer-Science-IA/IA/src/main/users.csv"};

        Library library = new Library(dirs);

        int expectedBookCapacity = 100;
        int resultBookCapacity = library.getBooksCapacity();
        assertEquals(expectedBookCapacity, resultBookCapacity);

        User expectedCurrentUser = new User("blank");
        User resultCurrentUser = library.getCurrentUser();
        assertEquals(expectedCurrentUser.getUsername(), resultCurrentUser.getUsername());

        Student student = new Student("student");
        library.addUser(student);
        library.loginStudent("student");
        User expectedCurrentUserLoggedIn = student;
        User resultCurrentUserLoggedIn = library.getCurrentUser();
        assertEquals(expectedCurrentUserLoggedIn, resultCurrentUserLoggedIn);

        library.logout();
        assertEquals(expectedCurrentUser.getUsername(), resultCurrentUser.getUsername());


        Library library1 = new Library(dirs,10);

        int expectedBookCapacity1 = 10;
        int resultBookCapacity1 = library1.getBooksCapacity();
        assertEquals(expectedBookCapacity1, resultBookCapacity1);

    }
}
