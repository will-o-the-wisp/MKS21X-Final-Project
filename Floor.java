import java.util.List;
import java.util.ArrayList;


public class Floor{
  private List<Room> rooms;
  //private List<Path> paths;
  private int entranceX;
  private int entranceY;
  private int exitX;
  private int exitY;
  private char[][] grid;

  public static void main (String[] args){
    Room r1 = new Room(1,1,7,5);
    Room r2 = new Room(8,8,3,4);
    ArrayList<Room> rs = new ArrayList<Room>();
    rs.add(r1);
    rs.add(r2);
    Floor f = new Floor(rs, 1, 1, 1, 1, 30, 20);
    f.printFloor();
  }
  Floor(List<Room> _rooms, int enX, int enY, int exX, int exY, int width, int height){
    rooms = _rooms;
    entranceX = enX;
    entranceY = enY;
    exitX = exX;
    exitY = exY;
    grid = new char[height][width];
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        grid[i][j]='_';
      }
    }
    for(int k=0;k<rooms.size();k++){
      for(int i=0;i<rooms.get(k).getHeight();i++){
        for(int j=0;j<rooms.get(k).getWidth();j++){
          grid[i+rooms.get(k).getTLCY()][j+rooms.get(k).getTLCX()]=
          rooms.get(k).getGrid()[i][j];
        }
      }
    }
  }
  Floor(int depth){

  }
  public void printFloor(){
    String ans="";
    for(int i=0;i<grid.length;i++){
      for(int j=0;j<grid[0].length;j++){
        ans+=grid[i][j]+" ";
      }
      ans+="\n";
    }
    System.out.println(ans);
  }
  public char[][] getGrid{
    return grid;
  }
  /*
  private void addRoom(Room r){
    int x = r.getTLCX();
    int y = r.getTLCY();
    for(int i=x;i<getWidth();i++){
      grid[i][y]='#'
    }
  }
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
