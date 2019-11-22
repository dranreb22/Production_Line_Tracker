package io.github.dranreb22;

import java.util.Date;

/**
 * Class ProductionRecord allows methods to record the production being done, as well enabling
 * formatting of things such as serial numbers of products.
 *
 * @author Bernard Georges 9/26/2019
 */

class ProductionRecord {

  private static int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;
  private static int countOfAU = 0;
  private static int countOfVI = 0;
  private static int countOfAM = 0;
  private static int countOfVM = 0;
  private String lastFive;

  /**
   * ProductionRecord constructor that accepts productID and sets default values to other
   * variables.
   *
   * @param productID The ID of the product pulled from the database.
   */

  ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
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
    productionNumber++;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }


  ProductionRecord(String manufacturer, int ID, ItemType itemType) {

    String firstThree = manufacturer.substring(0, 3);

    String itemCode = itemType.getItemType();

    if (itemCode.equals("AU")){
      lastFive = String.format("%05d", ++countOfAU);
    }
    if (itemCode.equals("VI")){
      lastFive = String.format("%05d", ++countOfVI);
    }
    if (itemCode.equals("AM")){
      lastFive = String.format("%05d", ++countOfAM);
    }
    else {
      lastFive = String.format("%05d", ++countOfVM);
    }

    serialNumber = firstThree + itemCode + lastFive;
    this.productID = ID;
    productionNumber++;
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
  public String getSerialNumber() {
    return serialNumber;
  }



  /**
   * Method enabling access to date the object was created.
   *
   * @return Returns the date the product was produced as a Date object.
   */
  public Date getDateProduced() {
    return dateProduced;
  }

  /**
   * Method enabling access to production number.
   *
   * @return Returns production number as an int.
   */
  public int getProductionNumber() {
    return productionNumber;
  }

  /**
   * Method that allows setting of a production number.
   *
   * @return Returns production number as an int.
   */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  @Override
  public String toString() {
    return "Prod. Num: " + productionNumber
        + " Product ID: " + productID
        + " Serial Num: " + serialNumber
        + " Date: " + dateProduced;
  }
}
