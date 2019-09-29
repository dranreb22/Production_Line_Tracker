package sample;

public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIOMOBILE("AM"),
  VISUALMOBILE("VM");

  final String ITEMTYPE;

  ItemType(String itemType) {
    ITEMTYPE = itemType;
  }

  public String getIemType() {
    return ITEMTYPE;
  }
}
