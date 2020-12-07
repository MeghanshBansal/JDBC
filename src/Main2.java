//PROBLEM STATEMENT;
//CREATE A DATABASE NAME gla AND A TABLE IN IT student;
//WRITE CODE FOR DELETE INSERT AND UPDATE SQL TABLE USING JDBC;

import java.sql.*;

public class Main2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gla";
        String user = "root";
        String pass = "";
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement statement = con.createStatement();
            String[] query = {"DELETE FROM `student`",
                    "INSERT INTO `student` (`rollno`, `name`, `email`, `cpi`) VALUES ('12', 'Nishant', 'nishant@gmail.com', '7.92')",
                    "INSERT INTO `student` (`rollno`, `name`, `email`, `cpi`) VALUES ('13', 'Aakash', 'Aakash@gmail.com', '9.22')",
                    "UPDATE student SET cpi=8.91 WHERE rollno=12"};
            for (int i = 0; i < query.length; i++) {
                statement.executeUpdate(query[i]);
            }
            ResultSet rs = statement.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                System.out.println(rs.getInt("rollno") + "\t" + rs.getString("name") + "\t" + rs.getString("email") + "\t" + rs.getFloat("cpi"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
