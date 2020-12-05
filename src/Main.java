import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = getDbConnection();
            if (con != null) {
                //statement
                Statement stmt = con.createStatement();

                String[] sql = {"DELETE FROM users",
                        "INSERT INTO `users` (`rollno`, `name`, `email`) VALUES ('1', 'Amit Mishra', 'amit.mishra@gmail.com'), ('2', 'Aakash', 'aakash@gmial.com'), ('3', 'Abhishake', 'abhishake@gmail.com')",
                        "SELECT * FROM users WHERE name='Aakash'",
                        "SELECT * FROM users"};
                //execute sql
                stmt.executeUpdate(sql[0]);
                stmt.executeUpdate(sql[1]);
                ResultSet rs;
                rs = stmt.executeQuery(sql[2]);
                while (rs.next()) {
                    System.out.println(rs.getInt("rollno") + "\t" + rs.getString("name") + "\t" + rs.getString("email") + "\n");
                }
                rs = stmt.executeQuery(sql[3]);
                //if rs having some data, by using boolean next() of ResultSet
                while (rs.next()) {
                    int rollno = rs.getInt("rollno");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    System.out.println(rollno + "\t" + name + "\t" + email);
                }
                rs.close();
                stmt.close();
                con.close();
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public static Connection getDbConnection() throws SQLException {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String pass = "";
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }
}