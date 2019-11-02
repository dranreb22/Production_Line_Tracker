package io.github.dranreb22;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 * @author Bernard Georges 9/26/2019 Controller class to control results of actions being done such
 * as a with a button click. It also establishes a connection to the database, enabling items to be
 * added to the database. It will eventually enable information to be pulled from a per instance
 * input or from the database, depending on the scope.
 */
public class ProductionTabController {

  @FXML
  private ComboBox<Integer> cmb_quantity;
  @FXML
  private ChoiceBox<ItemType> chb_itemType;
  @FXML
  private TextField txt_productName;
  @FXML
  private TextField txt_manufacturer;
  @FXML
  private TextArea textArea;
  @FXML
  private TableColumn<?,?> tbc_name;
  @FXML
  private TableColumn<?,?> tbc_manufacturer;
  @FXML
  private TableColumn<?,?> tbc_type;
  @FXML
  private TableView<Product> tbv_existingProducts;
  @FXML
  private ListView<Product> lvw_productOption;

  private ObservableList<Product> observableList;

  private DatabaseManager db = new DatabaseManager();

  @FXML
  public void initialize() {
    cmb_quantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //for (int p)
    for (ItemType it : ItemType.values()) {
      chb_itemType.getItems().add(it);
      chb_itemType.setValue(ItemType.AUDIO);
    }

    tbc_name.setCellValueFactory(new PropertyValueFactory("name"));
    tbc_manufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    tbc_type.setCellValueFactory(new PropertyValueFactory("itemType"));

    db.initializeDb();
    List<Product> availableProducts = db.getAvailableProducts();
    //System.out.println(availableProducts);
    observableList = FXCollections.observableArrayList(availableProducts);
    //observableList.add(new Widget("test","test", ItemType.AUDIO));
    //System.out.println("\n\n\n" + observableList);
    ProductionRecord record = new ProductionRecord(0);
    tbv_existingProducts.setItems(observableList);
    lvw_productOption.setItems(observableList);

//    try{
//      DatabaseManager database = new DatabaseManager();
//      productLine = database.getAvailableProducts();
//      for(int i = 0; i< productLine.size();i++){
//        productLine.
//      }
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }

    String productRecord = record.toString();

    textArea.setText(productRecord);
  }

  @FXML
  public void addProductClicked(){
    String prodName = txt_productName.getText();
    String prodMan = txt_manufacturer.getText();
    String chosenItem = chb_itemType.getValue().toString();

    db.addProduct(prodName, prodMan, chosenItem);

    observableList.add(new Widget(prodName, prodMan, ItemType.valueOf((chosenItem))));
    txt_productName.clear();
    txt_manufacturer.clear();

    //System.out.println(lvw_productOption.getSelectionModel().getSelectedItem().getName());
  }

  public void recordProduction(){
    System.out.println(lvw_productOption.getSelectionModel().getSelectedItems().get(1));
  }
}