import java.util.List;

public class Room{
  private int topLeftCornerX;
  private int topLeftCornerY;
  private int width;
  private int height;
  private char[][] grid;
  //private entities List<Entity>;
  public static void main (String[] args){
    Room r = new Room(1,1,7,5);
    r.printGrid();
  }

  public Room(int tlcX, int tlcY, int _width, int _height){
    topLeftCornerX=tlcX;
    topLeftCornerY=tlcY;
    width=_width;
    height=_height;
    //int x = tlcX;
    //int y = tlcY;
    grid=new char[height][width];
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        grid[i][j]='.';
      }
    }
    for(int i=0;i<width;i++){
      grid[0][i]='#';
    }
    for(int i=1;i<height-1;i++){
      grid[i][0]='#';
      grid[i][width-1]='#';
    }
    for(int i=0;i<width;i++){
      grid[height-1][i]='#';
    }
  }
  public void printGrid(){
    String ans="";
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        ans+=grid[i][j]+" ";
      }
      ans+="\n";
    }
    System.out.println(ans);
  }
  public char[][] getGrid(){
    return grid;
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
