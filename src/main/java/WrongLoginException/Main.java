package WrongLoginException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
       /*
       Exceptions. Checking your username and password
       Create a static method that takes three parameters as input: login, password, and confirmPassword.
       Login must contain only Latin letters, numbers and an underscore.
       The length of login must be less than 20 characters.
       If login does not meet these requirements, you must throw a WrongLoginException.

       Password must contain only Latin letters, numbers, and an underscore.
       The password must be less than 20 characters long. Also, password and confirmPassword must be equal.
       If password does not meet these requirements, you must throw a WrongPasswordException.
       WrongPasswordException and WrongLoginException - custom exception classes with two constructors - one by default,
       the second takes an exception message and passes it to the constructor of the Exception class.
       Exception handling is performed inside the method.
       We use a multi-catch block. The method returns true if the values are true or false otherwise.

        */
       private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{1,19}$");
    private static final Pattern PASS_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{1,19}$");

    public static void main(String[] args) {
        System.out.println(method("Add_123", "1234A_bC", "1234A_bC"));
        System.out.println(method("Add_123", "1234AbC?", "1234AbC?"));
        System.out.println(method("Add_123", "1234AbC", "1234AbCn"));
        System.out.println(method("Add_123?", "1234A_bC", "1234A_bC"));
    }

    public static boolean method(String login, String password, String confirmPassword) {
        try {
            Matcher loginMatcher = LOGIN_PATTERN.matcher(login);
            Matcher passMatcher = PASS_PATTERN.matcher(password);

            if (!confirmPassword.equals(password)) {
                throw new WrongPasswordException("Password does not match confirmPassword");
            }

            if (!passMatcher.matches()) {
                throw new WrongPasswordException("Password does not match requirements");
            }

            if (!loginMatcher.matches()) {
                throw new WrongLoginException("Login does not match requirements");
            }

            System.out.println("Login and Password is correct");
        } catch (WrongLoginException | WrongPasswordException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }
}
