package sample;

public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIOMOBILE("AM"),
  VISUALMOBILE("VM");

  final String itemType;

  ItemType(String itemType) {
    this.itemType = itemType;
  }

  public String getItemType() {
    return itemType;
  }
}
