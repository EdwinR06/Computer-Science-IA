package main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] dirs = {"/Users/edwinryerson/Documents/GitHub/Computer-Science-IA/IA/src/main/books.csv", "/Users/edwinryerson/Documents/GitHub/Computer-Science-IA/IA/src/main/teacher.csv", "/Users/edwinryerson/Documents/GitHub/Computer-Science-IA/IA/src/main/users.csv"};
        Library library = new Library(dirs);


        //library.addUser(new Student("student3"));

        //library.loginTeacher("admin", "password");
        System.out.println(library.getCurrentUser().getUsername());
        //library.loginStudent("student");
        //System.out.println(library.getCurrentUser().getUsername());
        
        //library.addBook(new Book("hhg", "hhg", "hjhjf", 40));
        //library.printBooks();
        //library.saveBooks("/Users/edwinryerson/IdeaProjects/IA/src/books.csv");

        //library.saveUsers("/Users/edwinryerson/IdeaProjects/IA/src/users.csv");

        new HomePage(library);
    }
}
