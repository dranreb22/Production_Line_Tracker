package io.github.dranreb22;

/**
 * Class Widget extends Product and is meant to serve as a generic type of product to be created.
 *
 * @author Bernard Georges 9/26/2019
 */

class Widget extends Product {

  /**
   * Constructor of class Widget that simply creates a widget with the fields of its superclass.
   *
   * @param name         Name of product
   * @param manufacturer Manufacturer of product
   * @param type         ItemType of product
   */

  Widget(Integer ID, String name, String manufacturer, ItemType type) {
    super(ID, name, manufacturer, type);
  }

}