package io.github.dranreb22;

import java.util.Date;

/**
 * Class ProductionRecord allows methods to record the production being done, as well enabling
 * formatting of things such as serial numbers of products.
 *
 * @author Bernard Georges 9/26/2019
 */

class ProductionRecord {

  private int productionNumber;
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
   * @param productionNumber Total number of items regardless of type.
   * @param productID        The ID of the product being produced.
   * @param serialNumber     The serial number of the items created depending on type.
   * @param dateProduced     The date of the item being produced (current date/time).
   */
  ProductionRecord(int productionNumber, int productID,
      String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  ProductionRecord(Product product, int count) {
    String manufacturer = product.getManufacturer();

    String firstThree = manufacturer.substring(0, 3);

    String itemCode = product.getItemType().getItemType();

    String lastFive = String.format("%05d", count);

    serialNumber = firstThree + itemCode + lastFive;

    this.dateProduced = new Date();

  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public int getProductionNumber() {
    return productionNumber;
  }

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
