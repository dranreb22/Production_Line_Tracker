package io.github.dranreb22;

import java.util.Date;
import javax.xml.bind.SchemaOutputResolver;

/**
 * Class ProductionRecord allows methods to record the production being done, as well enabling
 * formatting of things such as serial numbers of products.
 *
 * @author Bernard Georges 9/26/2019
 */

class ProductionRecord {

  private static int AUPRODUCTIONNUMBER;
  private static int VIPRODUCTIONNUMBER;
  private static int AMPRODUCTIONNUMBER;
  private static int VMPRODUCTIONNUMBER;
  private int productionNumber = 0;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

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

    if (itemCode.equals("AU")) {
      AUPRODUCTIONNUMBER++;
      lastFive = String.format("%05d", AUPRODUCTIONNUMBER);

    } else if (itemCode.equals("VI")) {
      VIPRODUCTIONNUMBER++;
      lastFive = String.format("%05d", VIPRODUCTIONNUMBER);

    } else if (itemCode.equals("AM")) {
      AMPRODUCTIONNUMBER++;
      lastFive = String.format("%05d", AMPRODUCTIONNUMBER);

    } else {
      VMPRODUCTIONNUMBER++;
      lastFive = String.format("%05d", VMPRODUCTIONNUMBER);

    }

    productionNumber =
        AUPRODUCTIONNUMBER + VIPRODUCTIONNUMBER + AMPRODUCTIONNUMBER + VMPRODUCTIONNUMBER;
    serialNumber = firstThree + itemCode + lastFive;
    this.productID = ID;
    this.dateProduced = new Date();

  }

  /**
   * Method enabling access to productID.
   *
   * @return Returns the productID as an int.
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Method enabling access to the serial number.
   *
   * @return Returns the serial number as a String object.
   */
  String getSerialNumber() {
    return serialNumber;
  }


  @Override
  public String toString() {
    return "Prod. Num: " + productionNumber
        + " Product ID: " + productID
        + " Serial Num: " + serialNumber
        + " Date: " + dateProduced + "\n";
  }
}
