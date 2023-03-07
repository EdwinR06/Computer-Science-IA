package Tests;

import org.junit.Test;

import main.Book;


import static org.junit.Assert.*;
public class TestAssertionsBook {
    @Test
    public void testAssertionsBook(){
        Book book1 = new Book("The", "author", "fantasy", 40);

        String expectedTitle = "The";
        String resultTitle = book1.getTitle();
        assertEquals(expectedTitle, resultTitle);

        String expectedAuthor = "author";
        String resultAuthor = book1.getAuthor();
        assertEquals(expectedAuthor, resultAuthor);

        String expectedGenre = "fantasy";
        String resultGenre = book1.getGenre();
        assertEquals(expectedGenre, resultGenre);

        int expectedPages = 40;
        int resultPages = book1.getPages();
        assertEquals(expectedPages, resultPages);

        boolean expectedBookCheckedOut = false;
        boolean resultBookCheckedOut = book1.getIsCheckedOut();
        assertEquals(expectedBookCheckedOut, resultBookCheckedOut);

        book1.setIsCheckedOut(true);
        boolean expectedBookCheckedOut1 = true;
        boolean resultBookCheckedOut1 = book1.getIsCheckedOut();
        assertEquals(expectedBookCheckedOut1, resultBookCheckedOut1);

    }
}
