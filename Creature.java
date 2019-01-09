public class Creature extends Interactive{

  private String direction;
  private int level;

  public void moveForward(){
    if (direction.equals("North")) {
      setY(getY()+1); //direction might not be correct these are based on normal x y axis
    }
    else if (direction.equals("East")) {
      setX(getX()+1); //direction might not be correct
    }
    else if (direction.equals("South")) {
      setY(getY()-1); //direction might not be correct
    }
    else if (direction.equals("West")) {
      setX(getX()-1); //direction might not be correct
    }
  }

  public void changeDirection(String dire){
    direction = dire;
  }

  public boolean meleeAttack(){
    return false;
  }

  public boolean rangeAttack(){
    return false;
  }
}
