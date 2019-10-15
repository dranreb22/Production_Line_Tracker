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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class to control results of actions being done such as a with a button click. It also
 * establishes a connection to the database, enabling items to be added to the database. It will
 * eventually enable information to be pulled from a per instance input or from the database,
 * depending on the scope.
 */
public class Controller {

  private Connection conn;
  private PreparedStatement pstmt;
  //private ResultSet rset;
  private String query;

  @FXML
  private ComboBox<Integer> cbbQuantity;
  @FXML
  private TextField txtFProductName;
  @FXML
  private TextField txtFManufacturer;
  @FXML
  private ChoiceBox<String> cbbItemType;
  @FXML
  private TextArea textArea = new TextArea();


  @FXML
  public void initialize() {
    cbbQuantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //for (int p)
    for (ItemType it : ItemType.values()) {
      //System.out.println(it);
      cbbItemType.getItems().add(it.toString());
    }
    initializeDb();
    //Execute a query
    ProductionRecord record = new ProductionRecord(0);

    String productRecord = record.toString();

    textArea.setText(productRecord);
  }

  private void initializeDb() {
    final String jdbcDriver = "org.h2.Driver";
    final String db_Url = "jdbc:h2:./res/production";

    //  Database credentials
    // to create a database username and password,
    // type Create USER [username] WITH PASSWORD "[password]"
    // to allow the user to edit the database use GRANT ALTER ANY SCHEMA TO [username]; in console
    final String user = "loginname";
    final String pass = "passw0rd";

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
  }


  @FXML
  public void addProductClicked() throws SQLException {
    initializeDb();
    String prodName = txtFProductName.getText();
    String prodMan = txtFManufacturer.getText();

    String chosenItem = cbbItemType.getValue();

    //Execute a query
    query = "INSERT INTO product(name, manufacturer, type) VALUES (?,?,?)";

    pstmt = conn.prepareStatement(query);
    pstmt.setString(1, prodName);
    pstmt.setString(2, prodMan);
    pstmt.setString(3, chosenItem);
    pstmt.executeUpdate();

    txtFProductName.clear();
    txtFManufacturer.clear();
  }
}