package main;

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
    private String[] dirs;

    public Library(String[] dirs) {
        this.dirs = dirs;
        this.booksCapacity = 100;
        this.users = new ArrayList<>();
        this.books = new Book[this.booksCapacity];
        this.currentUser = new User("blank");

        loadBooks(dirs[0]);
        loadTeacher(dirs[1]);
        loadUsers(dirs[2]);
    }

    public Library(String[] dirs, int booksCapacity) {
        this.dirs = dirs;
        this.booksCapacity = booksCapacity;
        this.users = new ArrayList<>();
        this.books = new Book[this.booksCapacity];
        this.currentUser = new User("blank");

        loadBooks(dirs[0]);
        loadTeacher(dirs[1]);
        loadUsers(dirs[2]);

        printBooks();
    }

    public String[] getDirs() {
        return dirs;
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

    public boolean addBook(Book book) {
        boolean flag = false;
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i] instanceof Book) {
                continue;
            } else if(books[i] == null) {
                books[i] = book;
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean deleteBook(String title) {
        boolean flag = false;
        if(currentUser instanceof Teacher) {
            Book book = getBookByTitle(title);
            for(int i = 0; i < books.length; i++) {
                if(books[i] == book) {
                    if(books[i].getIsCheckedOut()){
                        for(int j = 0; j < users.size(); i++){
                            if(!(users.get(j) instanceof Teacher) && ((Student)users.get(j)).getCheckedOutBook() == book) {
                                ((Student)users.get(j)).returnCurrentBook();
                            }
                        }
                    }
                    books[i] = null;
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private ArrayList<String[]> loadScanner(String dir) {
        File myFile1 = new File(dir);
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

            String[] r = line.trim().split(",");
            data.add(r);
        }
        return data;
    }

    private void loadBooks(String booksDir) {

        ArrayList<String[]> data = loadScanner(booksDir);
        boolean added = false;
        for(int i = 0; i < data.size(); i=(i+1)*4) {
            Book book = new Book(data.get(i)[0], data.get(i+1)[0], data.get(i+2)[0], Integer.parseInt(data.get(i+3)[0]));
            System.out.println(book.getTitle());
            added = addBook(book);
            if(added) {
                System.out.println("added");
                added = false;
            }
            
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

    private void loadUsers(String userDir) {

        ArrayList<String[]> data = loadScanner(userDir);

        for(int i = 0; i < data.size(); i++) {
            Student student = new Student(data.get(i)[0]);
            addUser(student);
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
            if(users.get(i) != null && !users.get(i).getUsername().equals("blank")) {
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

    private void loadTeacher(String teacherDir) {

        ArrayList<String[]> data = loadScanner(teacherDir);

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

    public boolean createStudent(String username) {
        boolean flag = false;
        if(currentUser instanceof Teacher) {
            boolean exists = false;
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getUsername().equals(username)) {
                    exists = true;
                }
            }

            if(!exists) {
                addUser(new Student(username));
                flag = true;
            }
        }
        return flag;
    }

    public void deleteStudent(String username) {
        if(currentUser instanceof Teacher) {
            boolean exists = false;
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getUsername().equals(username)) {
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

    public boolean loginTeacher(String username, String password) {
        boolean flag = false;
        if(currentUser == null || currentUser.getUsername().equals("blank")){
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i) instanceof Teacher){
                    if(users.get(i).getUsername().equals(username) &&  ((Teacher) users.get(i)).getPassword().equals(password)) {
                        currentUser = users.get(i);
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public void changeTeacherPassword(String password) {
        if(currentUser != null && currentUser instanceof Teacher) {
            ((Teacher) currentUser).setPassword(password);
        }
    }

    public boolean loginStudent(String username) {
        boolean flag = false;
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername().equals(username)) {
                currentUser = users.get(i);
                flag = true;
            }
        }
        return flag;
    }
    
    public void logout() {
        if(currentUser != null) {
            currentUser = new User("blank");
        }
    }

    public boolean checkoutBook(Book book) {
        boolean flag = false;
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i] == book && !books[i].getIsCheckedOut() && currentUser instanceof Student) {
                ((Student) currentUser).checkoutBook(book);
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean returnBookStudent() {
        boolean flag = false;
        if(currentUser instanceof Student && ((Student) currentUser).getCheckedOutBook() != null) {
            ((Student) currentUser).returnCurrentBook();
            flag = true;
        }
        return flag;
    }

    public boolean returnBookTeacher(Book book) {
        boolean flag = false;
        if(currentUser instanceof Teacher) {
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i) instanceof Student &&  ((Student) users.get(i)).getCheckedOutBook() == book){
                    ((Student) users.get(i)).returnCurrentBook();
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public Book getBookByTitle(String title) {
        Book bookTitle = null;
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i] != null && books[i].getTitle().equals(title)) {
                bookTitle = books[i];
            }
        }
        return bookTitle;
    }

    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> booksAuthor = new ArrayList<>();
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i].getAuthor().equals(author)) {
                booksAuthor.add(books[i]);
            }
        }
        return booksAuthor;
    }

    public ArrayList<Book> getBooksByGenre(String genre) {
        ArrayList<Book> booksGenre = new ArrayList<>();
        for(int i = 0; i < booksCapacity; i++) {
            if(books[i].getGenre().equals(genre)) {
                booksGenre.add(books[i]);
            }
        }
        return booksGenre;
    }

}
