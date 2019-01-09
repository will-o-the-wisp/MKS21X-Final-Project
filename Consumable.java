import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Consumable extends Item{

  private int duration;
  private String name;
  private List<String> types = new ArrayList<String>();
  private Random seed;

  public Consumable(){
    duration = 0;
    seed = new Random();
  }

  public void setDuration(int time){
    duration = time;
  }

  public int getDuration(){
    return duration;
  }

  public String getRandomConsum(){
    return types.get(seed.nextInt() % types.size());
  }
}
