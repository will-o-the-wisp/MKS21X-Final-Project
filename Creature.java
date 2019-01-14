public class Creature extends Interactive{

  private String direction;

  public void moveForward(Floor f){
    if (direction.equals("North")) {
      if (lookInFront(f) != '#'){
        setY(getY()-1); //direction might not be correct these are based on normal x y axis
      }
    }
    else if (direction.equals("East")) {
      if (lookInFront(f) != '#'){
          setX(getX()+1); //direction might not be correct
      }
    }
    else if (direction.equals("South")) {
      if (lookInFront(f) != '#'){
          setY(getY()+1); //direction might not be correct
      }
    }
    else if (direction.equals("West")) {
      if (lookInFront(f) != '#'){
          setX(getX()-1); //direction might not be correct
      }
    }
  }


  public void changeDirection(String dire){
    direction = dire;
  }

  public char lookInFront(Floor f){
    if (direction.equals("North") && (getY() != 0)) {
      return f.getGrid()[getY()-1][getX()]; //direction might not be correct these are based on normal x y axis
    }
    else if (direction.equals("West") && (getX() != 0)){
      return f.getGrid()[getY()][getX()-1]; //direction might not be correct
    }
    else if (direction.equals("East") && (getX() < f.getGrid()[0].length-1)) {
      return f.getGrid()[getY()][getX()+1]; //direction might not be correct
    }
    else if (direction.equals("South") && (getY() < f.getGrid().length-1)) {
      return f.getGrid()[getY()+1][getX()]; //direction might not be correct
    }
    return '#';
  }

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
