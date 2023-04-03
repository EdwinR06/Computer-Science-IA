package main;

public class Teacher extends User {
    // ie. Teacher is a Student, inherits from
    private String password;

    public Teacher(String username, String password) {
        super(username);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
