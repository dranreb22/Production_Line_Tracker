package io.github.dranreb22;

import io.github.dranreb22.interfaces.Item;

/**
 * Abstract class Product implementing Item interface and being extended by more specific products
 * (such as audioPlayer, MoviePlayer, etc).
 *
 * @author Bernard Georges 9/26/2019
 */
public abstract class Product implements Item {

  private int productID;
  private final ItemType itemType;
  private String manufacturer;
  private String name;

  /**
   * Constructor of product allowing user input of parameters name, manufacturer, and itemType.
   *
   * @param name         name of product.
   * @param manufacturer manufacturer of product.
   * @param itemType     itemType of product.
   */

  Product(String name, String manufacturer, ItemType itemType) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = itemType;
  }

  /**
   * Method that allows changing of the productID.
   * @param productID ID of the product from the database.
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * Method that enables access to the product.
   * @return ID of the product initially pulled from the database.
   */
  @Override
  public int getID() {
    return productID;
  }
  /**
   * Method that allows changing of the name.
   * @param name Name of the product from the database.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }
  /**
   * Method that enables access to the name of the product.
   * @return Name of the product initially pulled from the database or changed by setName.
   */
  @Override
  public String getName() {
    return name;
  }
  /**
   * Method that allows changing of the manufacturer.
   * @param manufacturer Manufacturer of the product from the database.
   */
  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Method that enables access to the itemType of the product.
   * @return itemType of the product initially pulled from the database.
   */
  public ItemType getItemType() {
    return this.itemType;
  }
  /**
   * Method that enables access to the manufacturer of the product.
   * @return manufacturer of the product initially pulled from the database.
   */
  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Method that returns to formatted information presented by the variables in this class.
   * @return Formatted String of productID + name + manufacturer + itemType.
   */
  @Override
  public String toString() {
    return "Product ID: " + productID + "\n"
        + "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + itemType;
  }
}