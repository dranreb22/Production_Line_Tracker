package io.github.dranreb22;

/**
 * ItemType enum sets predefined values for audio, visual, audio mobile, and visual mobile.
 *
 * @author Bernard Georges 9/26/2019
 */

public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIOMOBILE("AM"),
  VISUALMOBILE("VM");

  private final String itemType;

  /**
   * Constructor that sets the itemType from the defined parameter.
   *
   * @param itemType Predefined values of type of item.
   */
  ItemType(String itemType) {
    this.itemType = itemType;
  }

  /**
   * Method that enables access to the itemType.
   * @return The item type of the product/object.
   */
  public String getItemType() {
    return itemType;
  }
}
