public class Consumable extends Item{

  private int duration;

  public Consumable(){
    duration = 0;
  }

  public void setDuration(int time){
    duration = time;
  }

  public int getDuration(){
    return duration;
  }
}
