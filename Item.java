import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class Item extends Interactive{

  private static boolean onTheGround;
  private String type; //might not be necesasry

  public Item() throws FileNotFoundException{
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

  public static void setStatus(boolean status){
    onTheGround = status;
  }
}
