package onlineexamination;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineExamination {

    static class User {
        String username;
        String password;
        String profile;

        User(String username, String password, String profile) {
            this.username = username;
            this.password = password;
            this.profile = profile;
        }
    }

    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;
    private static boolean isExamRunning = false;
    private static int timer = 300; // 5 minutes for 5 questions

    public static void main(String[] args) {
        users.put("Josh", new User("Josh", "Josh123", "Default Profile"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                showLoginMenu(scanner);
            } else {
                showUserMenu(scanner);
            }
        }
    }

    private static void showLoginMenu(Scanner scanner) {
        System.out.println("1. Login");
        System.out.println("2. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            login(scanner);
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            currentUser = users.get(username);
            System.out.println("Login successful. Welcome " + currentUser.username + "!");
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    private static void showUserMenu(Scanner scanner) {
        System.out.println("1. Update Profile and Password");
        System.out.println("2. Start Exam");
        System.out.println("3. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            updateProfile(scanner);
        } else if (choice == 2) {
            startExam(scanner);
        } else if (choice == 3) {
            logout();
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }

    private static void updateProfile(Scanner scanner) {
        System.out.println("Enter new profile information:");
        String profile = scanner.nextLine();
        System.out.println("Enter new password:");
        String password = scanner.nextLine();

        currentUser.profile = profile;
        currentUser.password = password;

        System.out.println("Profile and password updated successfully.");
    }

    private static void startExam(Scanner scanner) {
        if (isExamRunning) {
            System.out.println("Exam is already running.");
            return;
        }

        isExamRunning = true;
        timer = 300; // 5 minutes for 5 questions

        new Thread(() -> {
            try {
                while (timer > 0) {
                    Thread.sleep(1000);
                    timer--;
                }
                if (isExamRunning) {
                    submitExam();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("Exam started. You have 5 minutes to complete.");
        askQuestion(scanner, "Q1: Which of the following option leads to the portability and security of Java?", new String[]{"1. Bytecode is executed by JVM", "2. The applet makes the Java code secure and portable", "3. Use of exception handling ", "4. Dynamic binding between objects"}, 1);
        askQuestion(scanner, "Q2: Which of the following is not a Java features??", new String[]{"1. Dynamic ", "2. Architecture Neutral", "3. Use of pointers ", "4. Object-oriented"}, 3);
        askQuestion(scanner, "Q3: _____ is used to find and fix bugs in the Java programs.", new String[]{"1. JVM ", "2. JRE", "3. JDK", "4. JDB"}, 4);
        askQuestion(scanner, "Q4: What is the return type of the hashCode() method in the Object class?", new String[]{"1. Object", "2. int", "3. long", "4. void"}, 2);
        askQuestion(scanner, "Q5: In which process, a local variable has the same name as one of the instance variables?", new String[]{"1. Serialization", "2. Variable Shadowing", "3. Abstraction", "4. Multi-threading"}, 2);

        submitExam();
    }

    private static void askQuestion(Scanner scanner, String question, String[] options, int correctAnswer) {
        System.out.println(question);
        for (String option : options) {
            System.out.println(option);
        }

        int answer = scanner.nextInt();
        scanner.nextLine();

        if (answer == correctAnswer) {
            System.out.println("Correct answer!");
        } else {
            System.out.println("Wrong answer.");
        }
    }

    private static void submitExam() {
        if (!isExamRunning) {
            return;
        }

        isExamRunning = false;
        System.out.println("Exam time is over. Exam submitted.");
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }
}
