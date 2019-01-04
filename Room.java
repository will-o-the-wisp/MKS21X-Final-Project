import java.util.List;

public class Room{
  private int botLeftCornerX;
  private int botLeftCornerY;
  private int width;
  private int height;
  //private entities List<Entity>;
  public Room(int blcX, int blcY, int _width, int _height){
    botLeftCornerX=blcX;
    botLeftCornerY=blcY;
    width=_width;
    height=_height;
  }
  public Room(int depth, int difficulty){

  }
  public int getBLCX(){
    return botLeftCornerX;
  }
  public int getBLCY(){
    return botLeftCornerY;
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
}
