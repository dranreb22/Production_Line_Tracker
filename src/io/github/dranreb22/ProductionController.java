package io.github.dranreb22;

import java.security.SecureRandom;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
  private TableView<Product> tbvExistingProducts;
  @FXML
  private ListView<Product> lvwProductOption;

  private ObservableList<Product> observableList;

  private final DatabaseManager db = new DatabaseManager();
  private SecureRandom random = new SecureRandom();
  private ArrayList<ProductionRecord> recordList = new ArrayList<>();

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

    db.initializeDb();
    observableList = FXCollections.observableArrayList(db.getAvailableProducts());
    tbvExistingProducts.setItems(observableList);
    lvwProductOption.setItems(observableList);
    lvwProductOption.getSelectionModel().selectFirst();

    db.closeDB();
  }

  //this was done in the fxml file by adding
  //<cellValueFactory><PropertyValueFactory property="manufacturer"/></cellValueFactory>
  /*public void setupProductLineTable() {
    tbcName.setCellValueFactory(new PropertyValueFactory("name"));
    tbcManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    tbcType.setCellValueFactory(new PropertyValueFactory("itemType"));
  }*/

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
    if (prodMan.length() < 4){
      prodMan = prodMan.toUpperCase();
    }
    else{
      prodMan = toCapital(prodMan);
    }
    String chosenItem = chbItemType.getValue().toString();
    //Integer ID;
    db.addProduct(prodName, prodMan, chosenItem);
    //db.ResetIDInTable();
    Product product = new Widget(Product.getNumberOfProducts(), prodName, prodMan, ItemType.valueOf((chosenItem)));
    observableList.add(product);
    txtProductName.clear();
    txtManufacturer.clear();
  }

  private String toCapital(String word){
    word = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    return word;
  }

  @FXML
  private void recordProductionClick() {
    try {
      ItemType selectedItemType = lvwProductOption.getSelectionModel().getSelectedItem().getItemType();
      String selectedManufacturer = lvwProductOption.getSelectionModel().getSelectedItem().getManufacturer();
      int selectedID = lvwProductOption.getSelectionModel().getSelectedItem().getID();
      int intID = Integer.parseInt(String.valueOf(selectedID));

      int numberOfItems = Integer.parseInt(String.valueOf(cmbQuantity.getValue()));

      for (int i = 0; i < numberOfItems; i++) {
        ProductionRecord record = new ProductionRecord(selectedManufacturer, intID, selectedItemType);
        String serialNumber = record.getSerialNumber();

          db.addToProductionDB(selectedID, serialNumber);
          recordList.add(record);
      }
      loadProductionLog(recordList);
    }
    catch (NullPointerException exception){
      exception.printStackTrace();
    }
  }
  /**
   * Method that initializes the production log from previous created products.
   */
  private void loadProductionLog(ArrayList<ProductionRecord> recordList) {
    textArea.clear();
    textArea.appendText(recordList.toString());
  }
}