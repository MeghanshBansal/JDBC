import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IllegalStateException {
        try {
            Connection con = getDbConnection();
            if (con != null) {
                Statement stmt = con.createStatement();
                char again = 'y';
                while (again == 'y') {
                    System.out.println("Enter your choice: ");
                    System.out.println("1. Clear table: ");
                    System.out.println("2. Insert into table: ");
                    System.out.println("3. Select query: ");
                    System.out.println("4. Show table: ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1 -> {
                            delete(stmt);
                            break;
                        }
                        case 2 -> {
                            insert(stmt);
                            break;
                        }
                        case 3 -> {
                            select(stmt);
                            break;
                        }
                        case 4 -> {
                            showtable(stmt);
                            break;
                        }
                        default -> {
                            System.out.println("Wrong choice !");
                        }
                    }
                    System.out.println("Do you want to execute again? Press y");
                    again = input.next().charAt(0);
                }
                stmt.close();
                con.close();
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
            throwable.printStackTrace();
        }
    }

    public static Connection getDbConnection() throws SQLException {
        Connection con;
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String pass = "";
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }

    public static ResultSet executestatement(Statement stmt, String sql) throws SQLException {
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public static void updatequery(Statement stmt, String sql) throws SQLException {
        stmt.executeUpdate(sql);
    }

    public static void delete(Statement stmt) throws SQLException {
        stmt.executeUpdate("DELETE FROM users");
    }

    public static void insert(Statement stmt) throws SQLException {
        System.out.println("Enter the roll number: ");
        int rollno = input.nextInt();
        input.nextLine();
        System.out.println("Enter the name: ");
        String name = input.nextLine();
        System.out.println("Enter the email: ");
        String email = input.nextLine();
        String sql = String.format("INSERT INTO `users` (`rollno`, `name`, `email`) VALUES ('%d', '%s', '%s')",rollno, name, email);
        updatequery(stmt, sql);
    }

    public static void select(Statement stmt) throws SQLException {
        input.nextLine();
        System.out.println("Enter the query clause: ");
        String query = input.nextLine();
        String sql = String.format("SELECT * FROM `users` WHERE %s;", query);
        System.out.println(sql);
        ResultSet rs = executestatement(stmt, sql);
        while (rs.next()) {
            int rollno = rs.getInt("rollno");
            String name = rs.getString("name");
            String email = rs.getString("email");
            System.out.println(rollno + "\t" + name + "\t" + email);
        }
    }

    public static void showtable(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM users";
        ResultSet rs = executestatement(stmt, sql);
        while (rs.next()) {
            int rollno = rs.getInt("rollno");
            String name = rs.getString("name");
            String email = rs.getString("email");
            System.out.println(rollno + "\t" + name + "\t" + email);
        }
    }
}