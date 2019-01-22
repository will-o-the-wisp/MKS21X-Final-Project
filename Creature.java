public class Creature extends Interactive{

  private String direction;

  public void moveForward(Floor f){
    if (lookInFront(f) != '#' && lookInFront(f) != ' ' && lookInFront(f) != '_' && lookInFront(f) != '!' && lookInFront(f) != '$'){
      if (direction.equals("North") && !(getY() <= 0)) {
        setY(getY()-1); //direction might not be correct
      }
      else if (direction.equals("East") && (getX() < f.getGrid()[0].length-1)) {
        setX(getX()+1); //direction might not be correct
      }
      else if (direction.equals("South") && (getY() < f.getGrid().length-1)) {
          setY(getY()+1); //direction might not be correct
      }
      else if (direction.equals("West") && !(getX() <= 0)) {
          setX(getX()-1); //direction might not be correct these are based on normal x y axis
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

  //use findEntity, use different cases for direction faced
  public boolean meleeAttack(Floor f){
    int x=getX();
    int y=getY();
    int a;
    int b;
    if(direction.equals("North")){
      a=x;
      b=y+1;
    }
    else if(direction.equals("East")){
      a=x+1;
      b=y;
    }
    else if(direction.equals("South")){
      a=x;
      b=y-1;
    }
    else if(direction.equals("West")){
      a=x-1;
      b=y;
    }
    else{
      a=0;
      b=0;
    }
    if(isEnemy(lookInFront(f))){
      Entity enemy = f.findEntity(a,b);
      enemy.setHP(enemy.getHP() - this.attackCalc(enemy));
      if (enemy.getHP() <= 0) {
        enemy.setAliveStatus(false);
      }
      return true;
    }
    return false;
  }
  public boolean isEnemy(char c){
    if(this instanceof Player){
      return c=='!';
    }
    if(this instanceof Monster){
      return c=='$';
    }
    return false;
  }
  public boolean rangeAttack(){
    return false;
  }

  public int attackCalc(Entity defender) {
    return this.getATK() - defender.getDEF();
  }

  public int defendCalc(Creature defender) {
    return defender.getATK() - this.getDEF();
  }

  public String directionTest(){
    return direction;
  }

  public int currentX(){
    return getX();
  }

  public int currentY(){
    return getY();
  }

  public boolean onExit(int x, int y){
    return (this.getX() == x && this.getY() == y);
  }

}
