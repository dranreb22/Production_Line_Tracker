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
   * @param productID        The ID of the product being produced.
   * @param serialNumber     The serial number of the items created depending on type.
   * @param dateProduced     The date of the item being produced (current date/time).
   */
  ProductionRecord(int productID,
      String serialNumber, Date dateProduced) {
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }


  ProductionRecord(String manufacturer, int ID, ItemType itemType) {
    if (auProductionCount != getCountOfAU() && amProductionCount!= getCountOfAM()
          && viProductionCount != getCountOfVI() && vmProductionCount != getCountOfVM()){
      auProductionCount = getCountOfAU()+1;
      amProductionCount = getCountOfVM()+1;
      viProductionCount = getCountOfVI()+1;
      vmProductionCount = getCountOfVM()+1;
    }
    System.out.println(auProductionCount);
    System.out.println(amProductionCount);
    System.out.println(viProductionCount);
    System.out.println(vmProductionCount);
    String firstThree;
    if (manufacturer.length() == 0){
      firstThree =  "XXX";
    }
    else if (manufacturer.length() == 1){
      firstThree = manufacturer.toUpperCase()+ "XX";
    }
    else if (manufacturer.length() == 2){
      firstThree = manufacturer.toUpperCase() + "X";
    }
    else {
      firstThree = manufacturer.toUpperCase().substring(0, 3);
    }

    String itemCode = itemType.getItemType();

    String lastFive;

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

  private int getCountOfAU() {
    countOfAU= db.getAUInDB();
    return countOfAU;
  }

  private int getCountOfAM() {
    countOfAM= db.getAMinDB();
    return countOfAM;
  }

  private int getCountOfVI() {
    countOfVI= db.getVIInDB();
    return countOfVI;
  }

  private int getCountOfVM() {
    countOfVM= db.getVMInDB();
    return countOfVM;
  }

  @Override
  public String toString() {
    return "Prod. Num: " + productionNumber
        + " Product ID: " + productID
        + " Serial Num: " + serialNumber
        + " Date: " + dateProduced + "\n";
  }
}
