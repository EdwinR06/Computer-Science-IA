package main;

import java.io.IOException;

public class Main {
    private final static String root = "src/main/";
    public static void main(String[] args) throws IOException {
        String[] dirs = {root + "books.csv", root + "teacher.csv", root + "users.csv"};
        Library library = new Library(dirs);

        new HomePage(library);
    }
}
