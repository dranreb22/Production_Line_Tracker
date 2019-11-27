package io.github.dranreb22;

import io.github.dranreb22.interfaces.Item;

/**
 * Abstract class Product implementing Item interface and being extended by more specific products
 * (such as audioPlayer, MoviePlayer, etc).
 *
 * @author Bernard Georges 9/26/2019
 */
public class Product implements Item {

  private int ID;
  private final ItemType itemType;
  private String manufacturer;
  private String name;
  private static int numberOfObjects = 1;

  /**
   * Constructor of product allowing user input of parameters name, manufacturer, and itemType.
   *
   * @param name         name of product.
   * @param manufacturer manufacturer of product.
   * @param itemType     itemType of product.
   */

  Product(Integer ID, String name, String manufacturer, ItemType itemType) {
    this.ID = ID;
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = itemType;
    numberOfObjects++;
  }

  Product(String name, String manufacturer, ItemType itemType) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = itemType;
    numberOfObjects++;
  }

  static int getNumberOfProducts(){
    return numberOfObjects;
  }
  /**
   * Method that allows changing of the ID.
   *
   * @param ID ID of the product from the database.
   */
  public void setID(Integer ID) {
    this.ID = ID;
  }

  /**
   * Method that enables access to the product.
   *
   * @return ID of the product initially pulled from the database.
   */
  @Override
  public int getID() {
    return ID;
  }

  /**
   * Method that allows changing of the name.
   *
   * @param name Name of the product from the database.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method that enables access to the name of the product.
   *
   * @return Name of the product initially pulled from the database or changed by setName.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Method that allows changing of the manufacturer.
   *
   * @param manufacturer Manufacturer of the product from the database.
   */
  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Method that enables access to the itemType of the product.
   *
   * @return itemType of the product initially pulled from the database.
   */
  ItemType getItemType() {
    return this.itemType;
  }

  /**
   * Method that enables access to the manufacturer of the product.
   *
   * @return manufacturer of the product initially pulled from the database.
   */
  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Method that returns to formatted information presented by the variables in this class.
   *
   * @return Formatted String of productID + name + manufacturer + itemType.
   */
  @Override
  public String toString() {
    return "Product ID: " + ID + "\n"
        + "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + itemType;
  }
}