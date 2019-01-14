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
  private Random rng; //move to Benelux later (?)

  public static void main (String[] args){


    ArrayList<Room> rs = new ArrayList<Room>();
    Random r = new Random();
    Floor f = new Floor(rs, 20, 15, 35, 20, r);
    Room r1 = new Room(1,2,3,4);
    f.addRoom(r1,1,2);
    f.addAllRooms();
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

  //move randFlip to Benelux later(?)
  public static int randFlip(Random r){
    if(r.nextInt(2)==0){
      return 1;
    }
    else{
      return -1;
    }
  }
  Floor(List<Room> _rooms, int exX, int exY, int width, int height, Random _rng){
    rooms = _rooms;

    exitX = exX;
    exitY = exY;
    rng = _rng;
    int w = rng.nextInt(5)+5;
    int h = rng.nextInt(5)+5;
    int x = rng.nextInt(6)+7;
    int y = rng.nextInt(6)+7;
    Room enroom = new Room(x,y,w,h);
    entranceX = enroom.getTLCX()+rng.nextInt(enroom.getWidth()-2)+1;
    entranceY = enroom.getTLCY()+rng.nextInt(enroom.getHeight()-2)+1;
    /*
    x = rng.nextInt(3)*randFlip(rng)+exitX;
    y = rng.nextInt(3)*randFlip(rng)+exitY;
    w = rng.nextInt(2)+5;
    h = rng.nextInt(2)+5;
    Room exroom = new Room(x,y,w,h);
    */
    rooms.add(enroom);
    //rooms.add(exroom);
    /*
    int numrooms = rng.nextInt(2)+3;
    for(int i=0;i<numrooms;i++){
    int x = rng.nextInt(15)+2;
    int y = rng.nextInt(15)+2;
    int w = rng.nextInt(7)+2;
    int h = rng.nextInt(7)+2;
    Room r1 = new Room(x,y,w,h);
    rooms.add(r1);
    }
    */
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
  private boolean addRoom(Room r, int x, int y){
    int w = r.getWidth();
    int h = r.getHeight();
    if(x+w>grid[0].length||y+h>grid.length){
      return false;
    }
    for(int i=0;i<h;i++){
      for(int j=0;j<w;j++){
        if(grid[y+i][x+j]!='_'){
          return false;
        }
      }
    }
    for(int i=0;i<h;i++){
      for(int j=0;j<w;j++){
        grid[y+i][x+j]=r.getGrid()[i][j];
      }
    }
    return true;
  }
  private void addAllRooms(){
    int tries=0;
    int fails=0;
    while(fails<grid.length*grid[0].length){
      Room r = new Room(rng.nextInt(grid[0].length*2/5)+6,
                        rng.nextInt(grid.length*2/5)+6);
      while(tries<grid.length*grid[0].length){
        if(!addRoom(r,rng.nextInt(grid[0].length),
                      rng.nextInt(grid.length)))
        {
          tries++;
        }
        else{
          tries=grid.length*grid[0].length;
          fails=0;
        }
      }
      tries=0;
      fails++;
    }
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
