import java.util.List;

public class Item extends Interactive{

  private boolean onTheGround;
  private String type;
  private List<String> typesOfItems;    //this needs to be defined and stored to be selected randomly to place?

  public Item(){
    onTheGround = true;
    type = "";
  }

  public Item(boolean status, String use) {
    onTheGround = status;
    type = use;
  }

  public String getType(){
    return type;
  }

  public void setType(String newTypes){
    type = newTypes;
  }

  public boolean getStatus(){
    return onTheGround;
  }

  public void setStatus(boolean status){
    onTheGround = status;
  }
}
