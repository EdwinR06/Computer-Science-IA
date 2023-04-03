package main;

import java.io.IOException;
/**
 * The Main program implements an application that
 * displays a library application for students and teachers to interact with in a classroom
 * library context
 *
 * @author  Edwin Ryerson
 * @version 1.0
 * @since   20233-04-02
 */
public class Main {
    private final static String root = "src/main/";
    public static void main(String[] args) throws IOException {
        String[] dirs = {root + "books.csv", root + "teacher.csv", root + "users.csv"};
        Library library = new Library(dirs); // libary instance

        new HomePage(library); // open GUI
    }
}
