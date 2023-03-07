package Tests;


import main.Book;
import main.Student;
import org.junit.Test;


import static org.junit.Assert.*;
public class TestAssertionsStudent {
    @Test
    public void testAssertionsStudent(){
        Student student = new Student("student");
        Book book = new Book("Where the Wild Things Are", "Michael Sednak", "Fantasy fiction", 40);

        student.checkoutBook(book);
        Book resultCheckedOutBook = student.getCheckedOutBook();
        assertEquals(book, resultCheckedOutBook);

        student.returnCurrentBook();
        Book resultCheckedOutBook1 = student.getCheckedOutBook();
        assertNull(null, resultCheckedOutBook1);
        boolean expectedBookIsCheckedOut = false;
        boolean resultBookIsCheckedOut = book.getIsCheckedOut();
        assertEquals(expectedBookIsCheckedOut, resultBookIsCheckedOut);


    }
}
