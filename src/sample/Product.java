package sample;


abstract class Product implements Item {
  private int id;
  private String type;
  private String manufacturer;
  private String name;

  Product (String name, String manufacturer, String type){
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }


  @Override
  public int getID(){
    return id;
  }

  @Override
  public void setName(String name){
    this.name = name;
  }

  @Override
  public String getName (){
    return name;
  }

  @Override
  public void setManufacturer(String manufacturer){
    this.manufacturer = manufacturer;
  }


  public void setType(String type){
    this.type = type;
  }

  public String getType(){
    return type;
  }
  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public String toString(){
    return "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + type;
  }
}
