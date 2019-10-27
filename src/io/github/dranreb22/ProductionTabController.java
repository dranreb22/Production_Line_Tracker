package io.github.dranreb22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.xml.crypto.Data;

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


  //private ResultSet rSet;

  @FXML
  public void initialize() {
    cmb_quantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //for (int p)
    for (ItemType it : ItemType.values()) {
      //System.out.println(it);
      chb_itemType.getItems().add(it);
      chb_itemType.setValue(ItemType.AUDIO);
    }

    DatabaseManager.initializeDb();
    ProductionRecord record = new ProductionRecord(0);

//    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
//      ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
//      // using the iterator as the product id for testing
//      System.out.println(pr.toString());
//    }

    String productRecord = record.toString();

    textArea.setText(productRecord);
  }

  @FXML
  public void addProductClicked(){
    String prodName = txt_productName.getText();
    String prodMan = txt_manufacturer.getText();
    String chosenItem = chb_itemType.getValue().toString();

    DatabaseManager.addProduct(prodName, prodMan, chosenItem);

    txt_productName.clear();
    txt_manufacturer.clear();
  }
}