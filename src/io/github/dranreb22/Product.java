package io.github.dranreb22;

import io.github.dranreb22.interfaces.Item;

/**
 * Abstract class Product implementing Item interface and being extended by more specific products
 * (such as audioPlayer, MoviePlayer, etc).
 *
 * @author Bernard Georges 9/26/2019
 */
public class Product implements Item {

  private int id;
  private ItemType itemType;
  private String manufacturer;
  private String name;
  private static int numberOfObjects = 1;

  /**
   * Constructor of product allowing user input of parameters name, manufacturer, and itemType.
   *
   * @param id           id of the product from the database.
   * @param name         name of product.
   * @param manufacturer manufacturer of product.
   * @param itemType     itemType of product.
   */

  Product(Integer id, String name, String manufacturer, ItemType itemType) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = itemType;
    numberOfObjects++;
  }

  /**
   * Constructor creating product object with values passed.
   *
   * @param name         Name of the product.
   * @param manufacturer Manufacturer of the product.
   * @param itemType     Item type of the product.
   */
  Product(String name, String manufacturer, ItemType itemType) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = itemType;
    numberOfObjects++;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }

  /**
   * Counter for number of products produced; enables tracking to create appropriate production
   * number.
   *
   * @return Number of products created.
   */
  static int getNumberOfProducts() {
    return numberOfObjects;
  }

  /**
   * Method that allows changing of the ID.
   *
   * @param id ID of the product from the database.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Method that enables access to the product.
   *
   * @return ID of the product initially pulled from the database.
   */
  @Override
  public int getId() {
    return id;
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
  public ItemType getItemType() {
    return itemType;
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
    return "Product ID: " + id + "\n"
        + "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + itemType;
  }
}