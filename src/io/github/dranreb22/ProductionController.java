package io.github.dranreb22;

import java.security.SecureRandom;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * <p>
 * Controller class to control results of actions being done such as a with a button click. It also
 * establishes a connection to the database, enabling items to be added to the database. It will
 * eventually enable information to be pulled from a per instance input or from the database,
 * depending on the scope.
 * </p>
 *
 * @author Bernard Georges 9/26/2019
 */
public class ProductionController {

  @FXML
  private ComboBox<Integer> cmbQuantity;
  @FXML
  private ChoiceBox<ItemType> chbItemType;
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufacturer;
  @FXML
  private TextArea textArea;
  @FXML
  private TableColumn<?, ?> tbcName;
  @FXML
  private TableColumn<?, ?> tbcManufacturer;
  @FXML
  private TableColumn<?, ?> tbcType;
  @FXML
  private TableView<Product> tbvExistingProducts;
  @FXML
  private ListView<Product> lvwProductOption;

  private ObservableList<Product> observableList;

  private final DatabaseManager db = new DatabaseManager();
  private SecureRandom random = new SecureRandom();

  /**
   * <p>
   * initialize method is the first method to run. it sets the values in the combo box, starts the
     * database with it's defined method from DatabaseManager class, gathers information from the
   * database and stores it into a list, which is then passed into an observable list,* and lastly
   * sets values to the text area, table view, and list view based on the results.
   * </p>
   */
  @FXML
  public void initialize() {
    cmbQuantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    for (ItemType it : ItemType.values()) {
      chbItemType.getItems().add(it);
      chbItemType.setValue(ItemType.AUDIO);
    }
    cmbQuantity.setValue(1);

//    setupProductLineTable();

    db.initializeDb();
    observableList = FXCollections.observableArrayList(db.getAvailableProducts());
    /*int randomValue = random.nextInt();
    ProductionRecord record = new ProductionRecord(randomValue);*/
    tbvExistingProducts.setItems(observableList);
    lvwProductOption.setItems(observableList);

    //String productRecord = record.toString();

    setupProductionLog();
    //textArea.setText(productRecord);
  }

  //this was done in the fxml file by adding
  //<cellValueFactory><PropertyValueFactory property="manufacturer"/></cellValueFactory>
  /*public void setupProductLineTable() {
    tbcName.setCellValueFactory(new PropertyValueFactory("name"));
    tbcManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    tbcType.setCellValueFactory(new PropertyValueFactory("itemType"));
  }*/

  /**
   * Method that initializes the production log from previous created products.
   */
  public void setupProductionLog() {
    textArea.clear();
    int randomValue = random.nextInt();
    ProductionRecord record = new ProductionRecord(randomValue);
    String productRecord = record.toString();
    textArea.setText(productRecord);
  }

  /**
   * <p>
   * method that runs when Add Product button is clicked, accepting the values from the text field,
   * storing them in the database through the method of addProduct(String, String, String) while
   * also storing them into the previously created observable list to update both the list view and
   * the table view. Clears the text fields.
   * </p>
   */
  @FXML
  public void addProductClicked() {
    String prodName = txtProductName.getText();
    String prodMan = txtManufacturer.getText();
    String chosenItem = chbItemType.getValue().toString();
    //Integer ID;
    db.addProduct(prodName, prodMan, chosenItem);
    //db.ResetIDInTable();
    System.out.println(prodName + " " + prodMan + " " + chosenItem);
    Product product = new Widget(Product.getNumberOfProducts()+1, prodName, prodMan, ItemType.valueOf((chosenItem)));
    observableList.add(product);
    /*System.out.println(observableList.indexOf());
    System.out.println(observableList.indexOf(prodName));
    System.out.println(observableList.indexOf(chosenItem));*/
    txtProductName.clear();
    txtManufacturer.clear();
  }

  @FXML
  public void recordProductionClick() {
    try {
      //Timestamp today = new Timestamp(new Date(), new CertPath());
      String selectedItem = lvwProductOption.getSelectionModel().getSelectedItem().getItemType().getItemType();
      String selectedManufacturer = lvwProductOption.getSelectionModel().getSelectedItem().getManufacturer();

      cmbQuantity.getValue();
      ArrayList<ProductionRecord> productionRun;
    }
    catch (NullPointerException exception){
      exception.printStackTrace();

    }

    System.out.println();
  }
}