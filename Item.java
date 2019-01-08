import java.util.List;

public class Item extends Interactive{

  private boolean onTheGround;
  private String type;
  
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
