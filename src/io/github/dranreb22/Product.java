package io.github.dranreb22;


import io.github.dranreb22.interfaces.Item;

abstract class Product implements Item {

  private int id;
  private ItemType itemType;
  private String manufacturer;
  private String name;

  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = type;
  }


  @Override
  public int getID() {
    return id;
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


  public void setItemType(ItemType type) {
    this.itemType = type;
  }

  ItemType getItemType() {
    return this.itemType;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public String toString() {
    return "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + itemType;
  }
}