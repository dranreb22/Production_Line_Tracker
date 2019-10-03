/**
 * @author Bernard Georges
 * @date 9/26/2019
 */

package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

/**
 * Controller class to control results of actions being done such as a with a button click. It also
 * establishes a connection to the database, enabling items to be added to the database. It will
 * eventually enable information to be pulled from a per instance input or from the database,
 * depending on the scope.
 */
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
    //for (int p)
    for (ItemType it: ItemType.values()) {
      //System.out.println(it);
      cbbItemType.getItems().add(it.toString());
    }



  }


  @FXML
  public void addProductClicked() {
    String prodName = txtFProductName.getText();
    String prodMan = txtFManufacturer.getText();
    String chosenItem = cbbItemType.getValue();

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/production";

    //  Database credentials
    // to create a database username and password, type Create USER [username] WITH PASSWORD "[password]"
    // to allow the user to edit the database use GRANT ALTER ANY SCHEMA TO [username]; in console
    final String USER = "loginname";
    final String PASS = "passw0rd";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      //attempting to use prepared statement as I was getting issues with
      //using the sql statements properly to input the information
      /*PreparedStatement getValues =
          conn.prepareStatement("INSERT INTO PRODUCT(name, type, manufacturer)
                 VALUES ('" + prodName + "',"
          + chosenItem + ',' + prodMan);
      getValues.executeUpdate();*/
      String sql =
          "INSERT INTO PRODUCT(name, type, manufacturer ) "
              + "VALUES ('" + prodName + "', '" + chosenItem + "', '" + prodMan + "')";

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

    txtFProductName.clear();
    txtFManufacturer.clear();
  }
}
