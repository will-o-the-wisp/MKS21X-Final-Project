import java.util.List;

public static class Room{
  private int botLeftCornerX;
  private int botLeftCornerY;
  private int width;
  private int height;
  Room(int blcX, int blcY, int _width, int _height){
    botLeftCornerX=blcX;
    botLeftCornerY=blcY;
    width=_width;
    height=_height;
  }
  Room(int depth){

  }
}
