import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Equipment extends Item{

  private int durability;
  private String name;
  private List<String> types = new ArrayList<String>();  //need a dictionary for this?
  private Random seed;

  public Equipment(){
    durability = 0;
    name = null;
    seed = new Random();
  }

  public String getRandomDrop(){
    return types.get(seed.nextInt() % types.size());
  }


}
