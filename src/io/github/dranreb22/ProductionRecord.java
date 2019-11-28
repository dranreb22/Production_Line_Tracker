package io.github.dranreb22;

import java.util.Date;
import javax.swing.undo.AbstractUndoableEdit;
import javax.xml.bind.SchemaOutputResolver;

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
  private Date dateProduced;
  private DatabaseManager db = new DatabaseManager();

  private int countOfAU;
  private int countOfAM;
  private int countOfVI;
  private int countOfVM;
  private static int auProductionCount;
  private static int amProductionCount;
  private static int viProductionCount;
  private static int vmProductionCount;


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
   * @param productID    The ID of the product being produced.
   * @param serialNumber The serial number of the items created depending on type.
   * @param dateProduced The date of the item being produced (current date/time).
   */
  ProductionRecord(int productID,
      String serialNumber, Date dateProduced) {
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
   * @param ID           The ID of the item from the database.
   * @param itemType     The item type of the item.
   */
  ProductionRecord(String manufacturer, int ID, ItemType itemType) {
    /*when the program starts, this checks that each of the static variables match
    the equivalent number of that item types' object. If not,
    they are set equal to that amount +1 (to prevent starting at 0)*/
    if (auProductionCount != getCountOfAU() && amProductionCount != getCountOfAM()
        && viProductionCount != getCountOfVI() && vmProductionCount != getCountOfVM()) {
      auProductionCount = getCountOfAU() + 1;
      amProductionCount = getCountOfVM() + 1;
      viProductionCount = getCountOfVI() + 1;
      vmProductionCount = getCountOfVM() + 1;
    }
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
        lastFive = String.format("%05d", ++auProductionCount);

        break;
      case "VI":
        lastFive = String.format("%05d", ++amProductionCount);

        break;
      case "AM":
        lastFive = String.format("%05d", ++viProductionCount);

        break;
      case "VM":
        lastFive = String.format("%05d", ++vmProductionCount);
        break;
      default:
        lastFive = "error";
        break;
    }

    //adds the values of all 4 item types; stores them as production number.
    productionNumber =
        auProductionCount + amProductionCount + viProductionCount + vmProductionCount;
    serialNumber = firstThree + itemCode + lastFive;
    this.productID = ID;
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
  private int getCountOfAU() {
    countOfAU = db.getAUInDB();
    return countOfAU;
  }

  /**
   * Method acquiring the number of items of type AM from the database.
   *
   * @return Returns the count of AM as an int.
   */
  private int getCountOfAM() {
    countOfAM = db.getAMinDB();
    return countOfAM;
  }

  /**
   * Method acquiring the number of items of type VI from the database.
   *
   * @return Returns the count of VI as an int.
   */
  private int getCountOfVI() {
    countOfVI = db.getVIInDB();
    return countOfVI;
  }

  /**
   * Method acquiring the number of items of type VM from the database.
   *
   * @return Returns the count of VM as an int.
   */
  private int getCountOfVM() {
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
    return "Prod. Num: " + productionNumber
        + " Product ID: " + productID
        + " Serial Num: " + serialNumber
        + " Date: " + dateProduced + "\n";
  }
}
