import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Floor{
  private List<Room> rooms;
  //private List<Path> paths;
  private int entranceX;
  private int entranceY;
  private int exitX;
  private int exitY;
  private char[][] grid;
  private Random rng; //move to Benelux later

  public static void main (String[] args){


    ArrayList<Room> rs = new ArrayList<Room>();
    Random r = new Random();
    Floor f = new Floor(rs, 3, 10, 20, 15, 30, 20, r);
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
  Floor(List<Room> _rooms, int enX, int enY, int exX, int exY, int width, int height, Random _rng){
    rooms = _rooms;
    entranceX = enX;
    entranceY = enY;
    exitX = exX;
    exitY = exY;
    rng = _rng;
    int x = Math.abs(rng.nextInt()%5)+2;
    int y = Math.abs(rng.nextInt()%5)+2;
    int w = Math.abs(rng.nextInt()%5)+2;
    int h = Math.abs(rng.nextInt()%5)+2;
    Room r1 = new Room(x,y,w,h);
    rooms.add(r1);
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
    grid[entranceY][entranceX]='~';
    grid[exitY][exitX]='*';
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
  public char[][] getGrid(){
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
