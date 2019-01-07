import java.util.List;

public class Room{
  private int topLeftCornerX;
  private int topLeftCornerY;
  private int width;
  private int height;
  //private entities List<Entity>;
  public Room(int tlcX, int tlcY, int _width, int _height){
    topLeftCornerX=tlcX;
    topLeftCornerY=tlcY;
    width=_width;
    height=_height;
  }
  public Room(int depth, int difficulty){

  }
  public int getTLCX(){
    return topLeftCornerX;
  }
  public int getTLCY(){
    return topLeftCornerY;
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
  /*
  public void drawRoom(Terminal t){
    int x = getTLCX();
    int y = getTLCY();
    t.moveCursor(x,y);
    for(int i=0;i<getWidth();i++){
      t.putCharacter('#');
    }
    for(int i=1;i<getHeight();i++){
      t.moveCursor(x,y+i);
      t.putCharacter('#');
      t.moveCursor(x+getWidth()-1,y+i);
      t.putCharacter('#');
    }
    t.moveCursor(x,y+getHeight());
    for(int i=0;i<getWidth();i++){
      t.putCharacter('#');
    }
  }
  */
}
