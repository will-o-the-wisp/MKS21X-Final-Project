public class Creature extends Interactive{

  private String direction;

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

  public void drawCreature(){

  }
/*
  public Entity lookInFront(){
    if (direction.equals("North")) {
      return getGrid[getY()+1][getX()]; //direction might not be correct these are based on normal x y axis
    }
    else if (direction.equals("East")) {
      return getGrid[getY()][getX()+1]; //direction might not be correct
    }
    else if (direction.equals("South")) {
      return getGrid[getY()-1][getX()]; //direction might not be correct
    }
    else if (direction.equals("West")) {
      return getGrid[getY()][getX()-1]; //direction might not be correct
    }
  }
*/
  public boolean meleeAttack(Creature defender){
    defender.setHP(defender.getHP() - attackCalc(defender));
    this.setHP(this.getHP() - defendCalc(defender));
    if (this.getHP() <= 0) {
      this.setAliveStatus(false);
    }
    if (defender.getHP() <= 0) {
      defender.setAliveStatus(false);
    }
    return true;
  }

  public boolean rangeAttack(){
    return false;
  }

  public int attackCalc(Creature defender) {
    return this.getATK() - defender.getDEF();
  }

  public int defendCalc(Creature defender) {
    return defender.getATK() - this.getDEF();
  }
}
