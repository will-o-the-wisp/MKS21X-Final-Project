import java.util.Random;

public class Monster extends Creature{

  private String species;

  public Monster(String s) {
    species = s;
  }

  public String getRandomDirection(){
    Random seed = new Random();
    int rands = seed.nextInt() % 3;
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

}
