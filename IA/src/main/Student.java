package main;

import java.util.ArrayList;

public class Student extends User {
    
    private Book checkedOutBook;

    public Student(String username) {
        super(username);
        this.checkedOutBook = null;
    }

    public Book getCheckedOutBook() {
        return checkedOutBook;
    }

    public void checkoutBook(Book book) {
        if(!book.getIsCheckedOut()) {
            this.checkedOutBook = book;
            book.setIsCheckedOut(true);
        }
    }

    public void returnCurrentBook() {
        checkedOutBook.setIsCheckedOut(false);
        checkedOutBook = null;
    }

}
