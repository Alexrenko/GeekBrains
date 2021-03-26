package FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Server.Auth;

import java.sql.*;
import java.util.ArrayList;

public class AuthenticationService {

    public AuthEntry findUserByCredentials(String login, String password) {
        String path = "C:\\DataBases\\AuthDB.db";
        try {
            ArrayList<AuthEntry> users = getUsersFromDB(path, login);
            for (AuthEntry entry : users) {
                if (login.equals(entry.getLogin()) && password.equals(entry.getPassword())) {
                return entry;
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<AuthEntry> getUsersFromDB(String DBPath, String login) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<AuthEntry> users = new ArrayList<>();
        String sqlQuery = String.format("SELECT * FROM Users WHERE Login = '%s'", login);

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            if (rs != null) {
                while (rs.next()) {
                    String dbLogin = rs.getString("Login");
                    String dbPassword = rs.getString("Password");
                    String dbNickname = rs.getString("Nickname");
                    users.add(new AuthEntry(dbLogin, dbPassword, dbNickname));
                }
            }
            return users;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try{
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
