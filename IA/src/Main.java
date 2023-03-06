import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Library library = new Library();
        library.loadTeacher("/Users/edwinryerson/IdeaProjects/IA/src/teacher.csv");
        library.loadUsers("/Users/edwinryerson/IdeaProjects/IA/src/users.csv");
        //library.addUser(new Student("student3"));
        //library.loadBooks("/Users/edwinryerson/IdeaProjects/IA/src/books.csv");
        library.loginTeacher("admin", "password");
        
        //library.addBook(new Book("hhg", "hhg", "hjhjf", 40));
        //library.printBooks();
        //library.saveBooks("/Users/edwinryerson/IdeaProjects/IA/src/books.csv");

        //library.saveUsers("/Users/edwinryerson/IdeaProjects/IA/src/users.csv");

        new HomePage(library);
    }
}
