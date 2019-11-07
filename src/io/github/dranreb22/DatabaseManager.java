package io.github.dranreb22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DatabaseManager provides methods to open access to the database, as well as to get and
 * store items from/within the database.
 *
 * @author Bernard Georges 9/26/2019
 */

class DatabaseManager {

  private Connection conn;
  private String productQuery;
  private PreparedStatement preparedStatement;
  private ResultSet result;

  /**
   * Opens the connection to the database.
   */
  void initializeDb() {

    try {
      // STEP 1: Register JDBC driver
      String jdbcDriver = "org.h2.Driver";
      Class.forName(jdbcDriver);

      //STEP 2: Open a connection
      String dbUrl = "jdbc:h2:./res/production";
      //Database credentials
      // to create a database username and password,
      // type Create USER [username] PASSWORD "[password]"
      // to allow the user to edit the database use GRANT ALTER ANY SCHEMA TO [username]; in console
      String user = "bernard";
      String pass = "georges";
      conn = DriverManager.getConnection(dbUrl, user, pass);

    } catch (ClassNotFoundException | SQLException exception) {
      exception.printStackTrace();

    }
  }

  /**
   * Method with preparedStatement enabling access to inserting items to the Product Table.
   *
   * @param name         Name of product.
   * @param manufacturer Manufacturer of product.
   * @param type         Item type of product.
   */
  void addProduct(String name, String manufacturer, String type) {
    String[] product = {name, manufacturer, type};
    int index = 1;
    try {

      //Execute a query
      productQuery = "INSERT INTO PRODUCT(NAME, MANUFACTURER, TYPE) VALUES(?,?,?)";
      preparedStatement = conn.prepareStatement(productQuery);
      for (String s : product) {
        preparedStatement.setString(index, s);
        index++;
      }
      preparedStatement.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Method with a prepared statement that returns the products in the Product table as a List of
   * type Product.
   *
   * @return Products from the database as a list of object type Product.
   */
  List<Product> getAvailableProducts() {
    List<Product> productLine = new ArrayList<>();
    try {
      productQuery = "SELECT * FROM PRODUCT";
      preparedStatement = conn.prepareStatement(productQuery);
      result = preparedStatement.executeQuery();

      while (result.next()) {
        Integer ID = result.getInt("ID");
        String name = result.getString("NAME");
        String manufacturer = result.getString("MANUFACTURER");
        String type = result.getString("TYPE");
        productLine.add(new Widget(ID, name, manufacturer, ItemType.valueOf(type)));
        /*if (type.equals("AUDIO")) {
          productLine.add(new AudioPlayer(name, manufacturer, ItemType.valueOf(type)));
        } else if (type.equals("VISUAL")) {
          //productLine.add(new MoviePlayer(name, manufacturer, ItemType.valueOf(type)));
        } else if (type.equals("VISUALMOBILE")) {
          //productLine.add(new AudioPlayer(name, manufacturer, ItemType.valueOf(type)));
        } else {
          //productLine.add(new AudioPlayer(name, manufacturer, ItemType.valueOf(type)));
        }*/
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return productLine;
  }

  /*public void testMethod() {

  }*/
}