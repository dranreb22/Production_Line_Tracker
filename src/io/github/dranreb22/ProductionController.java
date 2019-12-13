package io.github.dranreb22;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
  private TextField txtFirstName;
  @FXML
  private TextField txtLastName;
  @FXML
  private TextField txtPassword;
  @FXML
  private Label lblNotSelected;
  @FXML
  private TextArea txtProductionLog;
  @FXML
  private TextArea txtEmployeeLog;
  @FXML
  private TabPane tbpProduction;
  @FXML
  private Tab tabEmployeeRegistration;
  @FXML
  private Tab tabEmployee;
  @FXML
  private TableView<Product> tbvExistingProducts;
  @FXML
  private ListView<Product> lvwProductOption;

  private ObservableList<Product> observableList;

  private DatabaseManager db = new DatabaseManager();
  private ArrayList<ProductionRecord> recordList = new ArrayList<>();
  private ArrayList<ProductionRecord> initialList = new ArrayList<>();

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
    initialList = db.getRecordedProducts();
    loadInitialProducts();
    db.closeDB();
    tbpProduction.getSelectionModel().select(tabEmployeeRegistration);
  }
  private void loadInitialProducts(){
    for (ProductionRecord record: initialList){
      txtProductionLog.appendText(record.toString());
    }
  }

  @FXML
  public void registerEmployeeClick() {
    if (txtFirstName.getText().trim().equals("")) {
      txtFirstName.setPromptText("Please don't leave this empty");

    } else if (txtLastName.getText().trim().equals("")) {
      txtLastName.setPromptText("Please don't leave this empty");

    } else if (txtPassword.getText().trim().equals("")) {
      txtPassword.setPromptText("Please don't leave this empty");
    } else {
      //ensures there is always a space first between words
      String enteredName = txtFirstName.getText() + " " + txtLastName.getText();
      String[] splitName = enteredName.split(" ");
      // ensures only one space is visible in case user enters spaces in wrong place
      String name = splitName[0] + " " + splitName[1];
      String password = txtPassword.getText();
      String reversedPassword = reversePassword(password);
      Employee employee = new Employee(name, reversedPassword);

      txtEmployeeLog.setText(employee.toString());
      tbpProduction.getSelectionModel().select(tabEmployee);
      txtFirstName.clear();
      txtFirstName.setPromptText("");
      txtLastName.clear();
      txtLastName.setPromptText("");
      txtPassword.clear();
      txtPassword.setPromptText("");
    }
  }

  /**
   * Method that accepts the password entered by the employee and reverses it recursively.
   *
   * @param pw The password entered by the employee.
   * @return The reverse of the password.
   */
  private String reversePassword(String pw) {
    if (pw.isEmpty()) {
      return pw;
    }
    return reversePassword(pw.substring(1)) + pw.charAt(0);
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
    if (txtProductName.getText().trim().equals("")) {
      txtProductName.setPromptText("Please don't leave this empty");
    } else if (txtManufacturer.getText().trim().equals("")) {
      txtManufacturer.setPromptText("Please don't leave this empty");
    } else {
      String prodName = txtProductName.getText();
      String prodMan = txtManufacturer.getText();
      if (prodMan.length() < 4) {
        prodMan = prodMan.toUpperCase();
      } else {
        prodMan = toCapital(prodMan);
      }
      String chosenItem = chbItemType.getValue().toString();
      db.addProduct(prodName, prodMan, chosenItem);
      Product product = new Widget(Product.getNumberOfProducts(), prodName, prodMan,
          ItemType.valueOf((chosenItem)));
      observableList.add(product);
      txtManufacturer.setPromptText("");
      txtProductName.setPromptText("");
      txtProductName.clear();
      txtManufacturer.clear();
    }
  }

  /**
   * Capitalizes the first letter and ensures the remainder of the word is lower cased. Only to be
   * used with 1 word at a time.
   *
   * @param word The word to be capitalized.
   * @return Capitalized version of a word.
   */
  private String toCapital(String word) {
    word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    return word;
  }

  /**
   * <p>
   * Runs when the record production button is clicked. Takes the information of the selected item
   * in the list view and the quantity selected, creating new and storing new objects of that to the
   * record production database, as well as updating the production log
   * </p>
   */
  @FXML
  private void recordProductionClick() throws SQLException {
    if (lvwProductOption.getSelectionModel().getSelectedItem() == null) {
      lblNotSelected.setText("Please select an Item!");
      lblNotSelected.setVisible(true);
    } else {
      ItemType selectedItemType = lvwProductOption.getSelectionModel().getSelectedItem()
          .getItemType();
      String selectedManufacturer = lvwProductOption.getSelectionModel().getSelectedItem()
          .getManufacturer();
      String selectedName = lvwProductOption.getSelectionModel().getSelectedItem().getName();
      int selectedID = lvwProductOption.getSelectionModel().getSelectedItem().getId();
      //list view item wasn't properly converting to an int even though it was an int already
      //converted to an int then converted by to an int
      int intID = Integer.parseInt(String.valueOf(selectedID));

      int numberOfItems = Integer.parseInt(String.valueOf(cmbQuantity.getValue()));

      for (int i = 0; i < numberOfItems; i++) {
        ProductionRecord record = new ProductionRecord(selectedName, selectedManufacturer, intID,
            selectedItemType);
        String serialNumber = record.getSerialNumber();

        db.addToProductionDB(selectedName, selectedID, serialNumber);
        recordList.add(record);
      }
      loadProductionLog(recordList);
      if (lblNotSelected.isVisible()) {
        lblNotSelected.setVisible(false);
      }
    }
  }

  /**
   * Method that adds the newly created products to the log.
   */
  private void loadProductionLog(ArrayList<ProductionRecord> recordList) {
    txtProductionLog.clear();
    loadInitialProducts();
    for (ProductionRecord productionRecord : recordList) {
      txtProductionLog.appendText(productionRecord.toString());
    }
  }
}