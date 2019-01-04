/**
  *The group of objects that fill rooms
*/

public class Entity {

  //Since all entities are placed onto rooms and the terminal, there needs to be distinct positions for each entity
  private int x;
  private int y;

  /**
    *Function to acquire the x value of the Entity
    *
    *@return the x-coordinate of the entity
  */
  public int getX(){
    return x;
  }

  /**
    *Function to acquire the y value of the Entity
    *
    *@return the y-coordinate of the entity
  */
  public int getY(){
    return y;
  }
}
