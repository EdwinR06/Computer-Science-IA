import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private int booksCapacity;
    private ArrayList<User> users;
    private Book[] books;
    private User currentUser;

    public Library() {
        this.booksCapacity = 100;
        this.users = new ArrayList<>();
        this.books = new Book[this.booksCapacity];
        this.currentUser = new User("blank");
    }

    public Library(int booksCapacity) {
        this.booksCapacity = booksCapacity;
        this.users = new ArrayList<>();
        this.books = new Book[this.booksCapacity];
        this.currentUser = new User("blank");
    }

    public Book[] getBooks() {
        return books;
    }

    public void printBooks() {
        for(int i = 0; i < books.length; i++) {
            if(books[i] != null) {
                System.out.println(books[i]);
            }
        }
    }

    public void addBook(Book book) {
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i] instanceof Book) {
                continue;
            } else if(books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }

    public void deleteBook(String title) {
        if(currentUser instanceof Teacher) {
            Book book = getBookByTitle(title);
            for(int i = 0; i < books.length; i++) {
                if(books[i] == book) {
                    for(int j = 0; j < users.size(); i++){
                        if(((Student)users.get(j)).getCheckedOutBook() == book) {
                            ((Student)users.get(j)).returnCurrentBook();
                        }
                    }
                    books[i] = null;
                }
            }
        }
    }

    public void loadBooks(String booksDir) {

        File myFile1 = new File(booksDir);
        Scanner scanner = null;
        try {
            scanner = new Scanner(myFile1);
            scanner.useDelimiter(",");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }

        ArrayList<String[]> data = new ArrayList<>();

        while (scanner.hasNext()) {

            String line = scanner.next();

            String[] r = line.split(",");
            data.add(r);
        }

        for(int i = 0; i < data.size(); i=(i+1)*4) {
            Book book = new Book(data.get(i)[0], data.get(i+1)[0], data.get(i+2)[0], Integer.parseInt(data.get(i+3)[0]));
            addBook(book);
            
        }
    }

    public void saveBooks(String booksDir) throws IOException {
        File myFile = new File(booksDir);

        FileWriter fw = null;
        try {
            fw = new FileWriter(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < books.length; i++){
            if(books[i] != null) {
                try {
                    fw.write(String.format("%s,%s,%s,%s,", books[i].getTitle(), books[i].getAuthor(), books[i].getGenre(), books[i].getPages()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        fw.close();
    }

    public int getBooksCapacity() {
        return booksCapacity;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        boolean inUsers = false;
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i) == user) {
                inUsers = true;
                break;
            } 
        }
        if(!inUsers) {
            users.add(user);
        }
    }

    public void loadUsers(String userDir) {

        File myFile1 = new File(userDir);
        Scanner scanner = null;
        try {
            scanner = new Scanner(myFile1);
            scanner.useDelimiter(",");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }

        ArrayList<String[]> data = new ArrayList<>();

        while (scanner.hasNext()) {

            String line = scanner.next();

            String[] r = line.split(",");
            data.add(r);
        }

        for(int i = 0; i < data.size(); i++) {
            User user = new Student(data.get(i)[0]);
            addUser(user);
        }
    }

    public void saveUsers(String usersDir) throws IOException {
        File myFile = new File(usersDir);

        FileWriter fw = null;
        try {
            fw = new FileWriter(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(int i = 0; i < users.size(); i++){
            if(users.get(i) != null && users.get(i).getUsername() != "blank") {
                try {
                    System.out.println("'" + users.get(i).getUsername() + "'");
                    if(users.get(i) instanceof Student) {
                        fw.write(String.format("%s,", users.get(i).getUsername()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        fw.close();
    }

    public void loadTeacher(String teacherDir) {

        File myFile1 = new File(teacherDir);
        Scanner scanner = null;
        try {
            scanner = new Scanner(myFile1);
            scanner.useDelimiter(",");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }

        ArrayList<String[]> data = new ArrayList<>();

        while (scanner.hasNext()) {

            String line = scanner.next();

            String[] r = line.split(",");
            data.add(r);
        }

        for(int i = 0; i < data.size(); i=(i+1)*2) {
            User user = new Teacher(data.get(i)[0], data.get(i+1)[0]);
            addUser(user);
        }
    }

    public void saveTeacher(String teacherDir) throws IOException {
        File myFile = new File(teacherDir);

        FileWriter fw = null;
        try {
            fw = new FileWriter(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(int i = 0; i < users.size(); i++){
            if(users.get(i) != null) {
                try {
                    if(users.get(i) instanceof Teacher) {
                        fw.write(String.format("%s,%s,", users.get(i).getUsername(), ((Teacher) users.get(i)).getPassword()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        fw.close();
    }

    public void createStudent(String username) {
        if(currentUser instanceof Teacher) {
            boolean exists = false;
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getUsername() == username) {
                    exists = true;
                }
            }

            if(!exists) {
                addUser(new Student(username));
            }
        }
    }

    public void deleteStudent(String username) {
        if(currentUser instanceof Teacher) {
            boolean exists = false;
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getUsername() == username) {
                    if(((Student) users.get(i)).getCheckedOutBook() != null) {
                        ((Student) users.get(i)).returnCurrentBook();
                    }
                    users.remove(i);
                    break;
                }
            }
        }
    }

    public User getCurrentUser() {
        if(currentUser != null){ 
            return currentUser;
        } else {
            return null;
        }
    }

    public void loginTeacher(String username, String password) {
        if(currentUser == null || currentUser.getUsername() == "blank"){
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i) instanceof Teacher){
                    if(users.get(i).getUsername() == username &&  ((Teacher) users.get(i)).getPassword() == password) {
                        currentUser = users.get(i);
                    }
                }
            }
        }
    }

    public void changeTeacherPassword(String password) {
        if(currentUser != null && currentUser instanceof Teacher) {
            ((Teacher) currentUser).setPassword(password);
        }
    }

    public boolean loginStudent(String username) {
        boolean flag = false;
        if(currentUser == null || currentUser.getUsername() == "blank"){
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getUsername() == username) {
                    currentUser = users.get(i);
                    flag = true;
                }
            }
        }
        return flag;
    }
    
    public void logout() {
        if(currentUser != null) {
            currentUser = new User("blank");
        }
    }

    public void checkoutBook(Book book) {
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i] == book && books[i].getIsCheckedOut() == false && currentUser instanceof Student) {
                ((Student) currentUser).checkoutBook(book);
                break;
            }
        }
    }

    public Book getBookByTitle(String title) {
        Book bookTitle = null;
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i].getTitle() == title) {
                bookTitle = books[i];
            }
        }
        return bookTitle;
    }

    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> booksAuthor = new ArrayList<>();
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i].getAuthor() == author) {
                booksAuthor.add(books[i]);
            }
        }
        return booksAuthor;
    }

    public ArrayList<Book> getBooksByGenre(String genre) {
        ArrayList<Book> booksGenre = new ArrayList<>();
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i].getAuthor() == genre) {
                booksGenre.add(books[i]);
            }
        }
        return booksGenre;
    }

}
