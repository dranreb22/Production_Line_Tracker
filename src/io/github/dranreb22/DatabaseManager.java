package io.github.dranreb22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

  private static Connection conn;
  private static String addProductQuery;
  private static String delProductQuery;
  private static PreparedStatement addPrepStmt;

  public static Connection initializeDb() {
    final String jdbcDriver = "org.h2.Driver";
    final String db_Url = "jdbc:h2:./res/production";

    //  Database credentials
    // to create a database username and password,
    // type Create USER [username] WITH PASSWORD "[password]"
    // to allow the user to edit the database use GRANT ALTER ANY SCHEMA TO [username]; in console
    final String user = "admin";
    final String pass = "TestPassword";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(jdbcDriver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_Url, user, pass);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }

  public static void addProduct(String name, String manufacturer, String type) {
    String[] product = {name, manufacturer, type};
    int index = 1;
    try {

      //Execute a query
      addProductQuery = "INSERT INTO product(name, manufacturer, TYPE) VALUES (?,?,?)";

      addPrepStmt = conn.prepareStatement(addProductQuery);
      for (String s : product) {
        System.out.println(s);
        addPrepStmt.setString(index, s);
        index++;
      }
      addPrepStmt.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}