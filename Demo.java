import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO(Database.getConnection());
        ProductDAO productDAO = new ProductDAO(Database.getConnection());

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    register(scanner, userDAO);
                    break;
                case 2:
                    login(scanner, userDAO, productDAO);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void register(Scanner scanner, UserDAO userDAO) {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = PasswordUtil.hashPassword(scanner.next());
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Role (buyer/seller/admin): ");
        String role = scanner.next();

        User user = new User(0, username, password, email, role);
        try {
            userDAO.registerUser(user);
            System.out.println("Registration successful");
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void login(Scanner scanner, UserDAO userDAO, ProductDAO productDAO) {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        try {
            User user = userDAO.loginUser(username, password);
            if (user != null) {
                System.out.println("Login successful");
                showMenu(scanner, user, productDAO);
            } else {
                System.out.println("Invalid credentials");
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void showMenu(Scanner scanner, User user, ProductDAO productDAO) {
        switch (user.getRole()) {
            case "buyer":
                // Show buyer menu
                break;
            case "seller":
                // Show seller menu
                break;
            case "admin":
                // Show admin menu
                break;
        }
    }
}
