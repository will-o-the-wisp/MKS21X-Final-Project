import java.util.Random;

public class Monster extends Creature{

  private String species;

  public String getRandomDirection(){
    Random seed = new Random();
    int rands = seed.nextInt() % 4;
    if (rands == 0) {
      return "North";
    }
    if (rands == 1) {
      return "East";
    }
    if (rands == 2) {
      return "South";
    }
    return "West";
  }

  public void moveToPlayer(){
    
  }
  public void setRandomMovement(){

  }
}
