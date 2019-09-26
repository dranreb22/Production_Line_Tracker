package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

  @FXML
  private ComboBox<Integer> cbbQuantity;
  @FXML
  private TextField txtFProductName;
  @FXML
  private TextField txtFManufacturer;
  @FXML
  private ChoiceBox<String> cbbItemType;
  //@FXML private


  @FXML
  public void initialize() {
    cbbQuantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


  }

  @FXML
  public void addProductClicked() {
    System.out.println("Product Added");
    String prodName = txtFProductName.getText();
    String prodMan = txtFManufacturer.getText();

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/production";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql =
          "INSERT INTO PRODUCT(name, type, manufacturer ) VALUES ('" + prodName + "', " + "'AUDIO'"
              + ", '" + prodMan + "')";

      stmt.executeUpdate(sql);
      /*
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }
      */

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
