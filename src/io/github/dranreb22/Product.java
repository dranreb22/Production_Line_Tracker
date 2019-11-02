package io.github.dranreb22;

import io.github.dranreb22.interfaces.Item;

public abstract class Product implements Item {

  private int productID;
  private final ItemType itemType;
  private String manufacturer;
  private String name;

  Product(String name, String manufacturer, ItemType itemType) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = itemType;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  @Override
  public int getID() {
    return productID;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }


  public ItemType getItemType() {
    return this.itemType;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public String toString() {
    return "Product ID: " + productID + "\n"
        + "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + itemType;
  }
}