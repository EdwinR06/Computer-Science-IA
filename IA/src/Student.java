import java.util.ArrayList;

public class Student extends User {
    
    private Book checkedOutBook;
    private ArrayList<Book> pastCheckedOutBooks;

    public Student(String username) {
        super(username);
        this.checkedOutBook = null;
        this.pastCheckedOutBooks = new ArrayList<Book>();
    }

    public Book getCheckedOutBook() {
        return checkedOutBook;
    }

    public ArrayList<Book> getPastCheckedOutBooks() {
        return pastCheckedOutBooks;
    }

    public void checkoutBook(Book book) {
        if(book.getIsCheckedOut() == false) {
            this.checkedOutBook = book;
            book.setIsCheckedOut(true);
        }
    }

    public void returnCurrentBook() {
        checkedOutBook.setIsCheckedOut(false);
    }


    
}
