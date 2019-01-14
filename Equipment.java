import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Equipment extends Item{

  private int durability;
  private String name;
  private List<String> types = new ArrayList<String>();  //need a dictionary for this?
  private Random seed;

  public Equipment() throws FileNotFoundException{
    Scanner into = new Scanner(new File("equips.txt"));
    while (into.hasNext()) {
      types.add(into.nextLine());
    }
    seed = new Random();
    durability = seed.nextInt(100);
    name = getRandomDropName();
    //stats set?
  }

  public String getRandomDropName(){
    return types.get(seed.nextInt(types.size()));
  }

  public List<Integer> getRandomStats(){
    List<Integer> staters = new ArrayList<Integer>();
    staters.add(seed.nextInt(20));
    staters.add(seed.nextInt(10));
    staters.add(seed.nextInt(10));
    return staters;
  }

  public int getDurability(){
    return durability;
  }

  public void setDurability(int uses){
    durability = uses;
  }

  public String getName(){
    return name;
  }

  public void setName(String title){
    name = title;
  }

}
