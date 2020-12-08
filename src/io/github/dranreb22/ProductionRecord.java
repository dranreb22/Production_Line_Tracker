package io.github.dranreb22;

import java.sql.SQLException;
import java.util.Date;

/**
 * Class ProductionRecord allows methods to record the production being done, as well enabling
 * formatting of things such as serial numbers of products.
 *
 * @author Bernard Georges 9/26/2019
 */

class ProductionRecord {


  private int productionNumber = 0;
  private int productID;
  private String serialNumber;
  private String prodName;
  private Date dateProduced;
  private DatabaseManager db = new DatabaseManager();

  private int countOfAU;
  private int countOfAM;
  private int countOfVI;
  private int countOfVM;


  /**
   * ProductionRecord constructor that accepts productID and sets default values to other
   * variables.
   *
   * @param productID The ID of the product pulled from the database.
   */

  ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
    countOfAU = 0;
    countOfAM = 0;
    countOfVI = 0;
    countOfVM = 0;

  }

  /**
   * Production Record constructor enables the program to assign values to the variables of the
   * object.
   *
   * @param prodName     the name of the product from the database.
   * @param productID    The ID of the product being produced.
   * @param serialNumber The serial number of the items created depending on type.
   * @param dateProduced The date of the item being produced (current date/time).
   */
  ProductionRecord(String prodName, int productID, int productionNumber,
      String serialNumber, Date dateProduced) {
    this.prodName = prodName;
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }


  /**
   * Constructor that enables creates a new production record object, creating a serial number for
   * the object with the count of each type of object, its manufacturer, and its item type. Sets the
   * objects product ID to the ID passed in while settings the Date to today.
   *
   * @param manufacturer The manufacturer of the item.
   * @param id           The ID of the item from the database.
   * @param itemType     The item type of the item.
   */
  ProductionRecord(String prodName, String manufacturer, int id, ItemType itemType)
      throws SQLException {
    this.prodName = prodName;
    String firstThree;
    if (manufacturer.length() == 0) {
      firstThree = "XXX";
    } else if (manufacturer.length() == 1) {
      firstThree = manufacturer.toUpperCase() + "XX";
    } else if (manufacturer.length() == 2) {
      firstThree = manufacturer.toUpperCase() + "X";
    } else {
      firstThree = manufacturer.toUpperCase().substring(0, 3);
    }

    String itemCode = itemType.getItemType();

    String lastFive;

    // switch statement to set the serial number specifically to the next value of its item type.
    switch (itemCode) {
      case "AU":
        lastFive = String.format("%05d", 1 + getCountOfAU());

        break;
      case "AM":
        lastFive = String.format("%05d", 1 + getCountOfAM());
        break;
      case "VI":
        lastFive = String.format("%05d", 1 +getCountOfVI());

        break;
      case "VM":
        lastFive = String.format("%05d", 1 + getCountOfVM());
        break;
      default:
        lastFive = "error";
        break;
    }

    //adds the values of all 4 item types; stores them as production number.
    productionNumber =
        getCountOfAU() + getCountOfAM() + getCountOfVI() + getCountOfVM();
    serialNumber = firstThree + itemCode + lastFive;
    this.productID = id;
    this.dateProduced = new Date();

  }

  /**
   * Method enabling access to the serial number.
   *
   * @return Returns the serial number as a String object.
   */
  String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Method acquiring the number of items of type AU from the database.
   *
   * @return Returns the count of AU as an int.
   */
  private int getCountOfAU() throws SQLException {
    countOfAU = db.getAUInDB();
    return countOfAU;
  }

  /**
   * Method acquiring the number of items of type AM from the database.
   *
   * @return Returns the count of AM as an int.
   */
  private int getCountOfAM() throws SQLException {
    countOfAM = db.getAMInDB();
    return countOfAM;
  }

  /**
   * Method acquiring the number of items of type VI from the database.
   *
   * @return Returns the count of VI as an int.
   */
  private int getCountOfVI() throws SQLException {
    countOfVI = db.getVIInDB();
    return countOfVI;
  }

  /**
   * Method acquiring the number of items of type VM from the database.
   *
   * @return Returns the count of VM as an int.
   */
  private int getCountOfVM() throws SQLException {
    countOfVM = db.getVMInDB();
    return countOfVM;
  }

  /**
   * Formats information from class.
   *
   * @return A formatted version of the product number, id, serial number, and date produced.
   */
  @Override
  public String toString() {
    return "Prod. Name: " + prodName
        + " Prod. Num: " + productionNumber
        + " Product ID: " + productID
        + " Serial Num: " + serialNumber
        + " Date: " + dateProduced + "\n";
  }
}
