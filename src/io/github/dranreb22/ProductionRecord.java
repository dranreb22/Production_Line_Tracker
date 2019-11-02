package io.github.dranreb22;

import java.util.Date;

class ProductionRecord {

  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  ProductionRecord(int productionNumber, int productID,
      String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  ProductionRecord(Product product, int count) {
    String manufacturer = product.getManufacturer();

    // String countInNumbers = String.valueOf(count);
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
