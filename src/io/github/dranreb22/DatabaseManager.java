package io.github.dranreb22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

      Properties prop = new Properties();
      prop.load(new FileInputStream("res/properties"));
      String pass = prop.getProperty("password");
      String user = prop.getProperty("username");
      conn = DriverManager.getConnection(dbUrl, user, pass);

    } catch (ClassNotFoundException | SQLException | IOException exception) {
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
      productQuery = "INSERT INTO PRODUCT(NAME, MANUFACTURER, TYPE) VALUES(?,?,?);";
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
      productQuery = "SELECT * FROM PRODUCT;";
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

  //void AddProductRecordDB(String name){
/*    String[] product = {name, manufacturer, type};
    int index = 1;
    try {

      //Execute a query
      productQuery = "INSERT INTO PRODUCT(NAME, MANUFACTURER, TYPE) VALUES(?,?,?);";
      preparedStatement = conn.prepareStatement(productQuery);
      for (String s : product) {
        preparedStatement.setString(index, s);
        index++;
      }
      preparedStatement.executeUpdate();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }*/

/*  public void ResetIDInTable() {
    try {
      productQuery = "ALTER TABLE PRODUCT DROP COLUMN ID; ALTER TABLE PRODUCT ADD ID INT NOT NULL AUTO_INCREMENT BEFORE NAME; CREATE PRIMARY KEY ON PRODUCT (ID);";
      preparedStatement = conn.prepareStatement(productQuery);
      preparedStatement.executeUpdate();

    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }*/
  /*public void testMethod() {

  }*/
}