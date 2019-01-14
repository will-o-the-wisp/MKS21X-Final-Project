import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Consumable extends Item{

  private int duration;
  private String name;
  private List<String> types = new ArrayList<String>();
  private Random seed;

  public Consumable(){
    //this.super(getRandomStats());  stat setting?
    Scanner into = new Scanner(new File("times.txt"));
    while (into.hasNext()) {
      types.add(into.nextLine());
    }
    seed = new Random();
    duration = seed.nextInt(100);
    name = getRandomConsum();
    //this.super().setStats() = getRandomStats();
  }

  public void setDuration(int time){
    duration = time;
  }

  public int getDuration(){
    return duration;
  }

  public String getRandomConsum(){
    return types.get(seed.nextInt(types.size()));
  }

  public List<Integer> getRandomStats(){
    List<Integer> staters = new ArrayList<Integer>();
    staters.add(seed.nextInt(20));
    staters.add(seed.nextInt(10));
    staters.add(seed.nextInt(10));
    return staters;
  }
}
