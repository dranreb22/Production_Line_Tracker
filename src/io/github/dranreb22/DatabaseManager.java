package io.github.dranreb22;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
   * Closes database connection.
   */
  public void closeDB() {
    try {
      result.close();
      preparedStatement.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
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
    initializeDb();
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
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeDB();
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
        Integer id = result.getInt("ID");
        String name = result.getString("NAME");
        String manufacturer = result.getString("MANUFACTURER");
        String type = result.getString("TYPE");
        if (type.equals("AUDIO")) {
          productLine.add(new AudioPlayer(id, name, manufacturer));
        } else if (type.equals("VISUAL")) {
          productLine.add(new MoviePlayer(id, name, manufacturer, ItemType.valueOf(type),
              new Screen(null, 0, 0), MonitorType.LCD));
        } else {
          productLine.add(new Widget(id, name, manufacturer, ItemType.valueOf(type)));
        }
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeDB();
    }
    return productLine;
  }

  /**
   * Method that returns the number of items in the database. Enables the program to know what the
   * production number should start at on every
   *
   * @param itemCode The item type being called
   * @return The items in the database whose serial number matches the Type.
   */
  private int getCountOfItems(String itemCode) throws SQLException {
    initializeDb();
    int itemCount = 0;
    try {
      productQuery = "SELECT SERIAL_NUM FROM PRODUCTIONRECORD WHERE INSTR(SERIAL_NUM, ?);";
      preparedStatement = conn.prepareStatement(productQuery);
      preparedStatement.setString(1, itemCode);
      result = preparedStatement.executeQuery();
      while (result.next()) {
        if (result.getString("SERIAL_NUM").substring(3, 5).equals(itemCode)) {
          itemCount++;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemCount;
  }

  /**
   * Gets the AU count from the database.
   *
   * @return The count of serial numbers that contains AU.
   */
  int getAUInDB() throws SQLException {
    return getCountOfItems("AU");
  }

  /**
   * Gets the AM count from the database.
   *
   * @return The count of serial numbers that contains AM.
   */
  int getAMInDB() throws SQLException {
    return getCountOfItems("AM");
  }

  /**
   * Gets the VI count from the database.
   *
   * @return The count of serial numbers that contains VI.
   */
  int getVIInDB() throws SQLException {
    return getCountOfItems("VI");

  }

  /**
   * Gets the VM count from the database.
   *
   * @return The count of serial numbers that contains VM.
   */
  int getVMInDB() throws SQLException {
    return getCountOfItems("VM");
  }

  /**
   * Method including sql query enabling a recently produced product to be added into the database.
   *
   * @param id           The product ID of the database.
   * @param serialNumber The serial number generated from the production record class.
   */
  void addToProductionDB(String name, int id, String serialNumber) {
    initializeDb();
    try {
      //Execute a query
      productQuery = "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID, NAME, SERIAL_NUM, DATE_PRODUCED) "
          + "VALUES(?,?, ?,?);";
      preparedStatement = conn.prepareStatement(productQuery);
      preparedStatement.setInt(1, id);
      preparedStatement.setString(3, serialNumber);
      preparedStatement.setString(2, name);
      Date now = new Date();
      Timestamp ts = new Timestamp(now.getTime());
      preparedStatement.setTimestamp(4, ts);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeDB();
    }
  }

  public ArrayList<ProductionRecord> getRecordedProducts() {
    initializeDb();
    ArrayList<ProductionRecord> recordedProducts = new ArrayList<>();
    try {
      productQuery = "SELECT * FROM PRODUCTIONRECORD;";
      preparedStatement = conn.prepareStatement(productQuery);
      result = preparedStatement.executeQuery();
      while (result.next()) {
        int id = result.getInt("PRODUCT_id");
        String name = result.getString("NAME");
        int productionNum = result.getInt("PRODUCTION_NUM");
        String serialNum = result.getString("SERIAL_NUM");
        Timestamp dateProduced = result.getTimestamp("DATE_PRODUCED");
        recordedProducts.add(new ProductionRecord(name, id, productionNum, serialNum, dateProduced));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeDB();
    }
    return recordedProducts;
  }
}