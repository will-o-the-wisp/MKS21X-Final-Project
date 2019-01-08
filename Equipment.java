import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Equipment extends Item{

  private int durability;
  private String name;
  private List<String> types = new ArrayList();

  public Equipment(){
    durability = 0;
    name = null;
  }

  public Equipment getRandomDrop(){

  }
}
