package io.github.dranreb22;

public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIOMOBILE("AM"),
  VISUALMOBILE("VM");

  private final String itemType;

  ItemType(String itemType) {
    this.itemType = itemType;
  }

  public String getItemType() {
    return itemType;
  }
}
