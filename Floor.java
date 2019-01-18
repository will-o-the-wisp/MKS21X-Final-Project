import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Floor{
  private ArrayList<Room> rooms;
  //private List<Path> paths;
  private int entranceX;
  private int entranceY;
  private int exitX;
  private int exitY;
  private char[][] grid;
  private Random rng; //move to Benelux later (?)

  public int indexOfRoom(int x, int y){
    for(int i=0;i<rooms.size();i++){
      Room r=rooms.get(i);
      if(x>=r.getTLCX()&&
         x<=r.getTLCX()+r.getWidth()-1&&
         y>=r.getTLCY()&&
         y<=r.getTLCY()+r.getHeight()-1){
           return i;
         }
    }
    return -1;
  }
  public Entity findEntity(int x, int y){
    /*
    use indexOfRoom, set up list of entities in Room.java
    */
    return null;
  }
  public static void main(String[] args){
    ArrayList<Room> rs = new ArrayList<Room>();
    Random r = new Random();
    Floor f = new Floor(rs, 35, 20, r);
    Room r1 = new Room(3, 5);
    Room r2 = new Room(5, 3);
    f.addAllRooms();
    f.addEntrance();
    f.addExit();
    f.addAllPaths();
    f.printFloor();
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
  Floor(ArrayList<Room> _rooms, int width, int height, Random _rng){
    rooms = _rooms;
    rng = _rng;
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
        if(grid[i][j]!='_'){
          ans+=grid[i][j];
        }
        else{
          ans+=" ";
        }
        ans+=" ";
      }
      ans+="\n";
    }
    System.out.println(ans);
  }
  //get methods
  public int getEntranceX(){
    return entranceX;
  }
  public int getEntranceY(){
    return entranceY;
  }
  public int getExitX(){
    return exitX;
  }
  public int getExitY(){
    return exitY;
  }
  public char[][] getGrid(){
    return grid;
  }
  public boolean addRoom(Room r, int x, int y){
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
  public boolean addPath(int x, int y, int w, int h, int xi, int yi){
    if(x+w*xi>grid[0].length-1||
      x+w*xi<0||
      y+h*yi>grid.length-1||
      y+h*yi<0||
      grid[y][x]!='#'||
      grid[y+yi*(h-1)][x+xi*(w-1)]!='#'||
      (xi==0&&yi==0)){
      return false;
    }
    for(int i=1;i<h-1;i++){
      if(grid[y+i*yi][x]!='_'){
        return false;
      }
    }
    for(int j=1;j<w-1;j++){
      if(grid[y+yi*(h-1)][x+j*xi]!='_'){
        return false;
      }
    }
    for(int i=0;i<h;i++){
      grid[y+i*yi][x]='.';
    }
    for(int j=0;j<w;j++){
      grid[y+yi*(h-1)][x+j*xi]='.';
    }
    return true;
  }
  public void addAllPaths(){
    int tries=0;
    int fails=0;
    int paths=0;
    int lim=grid.length*grid[0].length*10;
    ArrayList<Room>roomsToConnect=new ArrayList<Room>();
    for(int i=0;i<rooms.size();i++){
      roomsToConnect.add(rooms.get(i));
    }
    int x;
    int y;
    while(fails<lim&&roomsToConnect.size()>0){
          Room r=roomsToConnect.get(
            rng.nextInt(roomsToConnect.size()));
          int rint=rng.nextInt(4);
          int w=rng.nextInt(6)+2;
          int h=rng.nextInt(5)+2;
          int xi=rng.nextInt(3)-1;
          int yi=rng.nextInt(3)-1;
          while(tries<r.getWidth()*r.getHeight()*10){
            if(rint==0){
              x=r.getTLCX()+rng.nextInt(r.getWidth()-2)+1;
              y=r.getTLCY();
              yi=Floor.randFlip(rng);
            }
            else if(rint==1){
              x=r.getTLCX()+rng.nextInt(r.getWidth()-2)+1;
              y=r.getTLCY()+r.getHeight()-1;
              yi=Floor.randFlip(rng);
            }
            else if(rint==2){
              x=r.getTLCX();
              y=r.getTLCY()+rng.nextInt(r.getHeight()-2)+1;
              xi=Floor.randFlip(rng);
            }
            else{
              x=r.getTLCX()+r.getWidth()-1;
              y=r.getTLCY()+rng.nextInt(r.getHeight()-2)+1;
              xi=Floor.randFlip(rng);
            }
            if(!addPath(x,y,w,h,xi,yi)){
              tries++;
            }
            else{
              roomsToConnect.remove(r);
              tries=lim;
              paths++;
              fails=0;
            }
          }
          tries=0;
          fails++;
  }
  while(fails<lim&&paths<rooms.size()*3/2){
          Room r=rooms.get(
            rng.nextInt(rooms.size()));
          int rint=rng.nextInt(4);
          int w=rng.nextInt(6)+3;
          int h=rng.nextInt(5)+2;
          int xi=rng.nextInt(3)-1;
          int yi=rng.nextInt(3)-1;
          while(tries<r.getWidth()*r.getHeight()){
            if(rint==0){
              x=r.getTLCX()+rng.nextInt(r.getWidth()-2)+1;
              y=r.getTLCY();
              yi=Floor.randFlip(rng);
            }
            else if(rint==1){
              x=r.getTLCX()+rng.nextInt(r.getWidth()-2)+1;
              y=r.getTLCY()+r.getHeight()-1;
              yi=Floor.randFlip(rng);
            }
            else if(rint==2){
              x=r.getTLCX();
              y=r.getTLCY()+rng.nextInt(r.getHeight()-2)+1;
              xi=Floor.randFlip(rng);
            }
            else{
              x=r.getTLCX()+r.getWidth()-1;
              y=r.getTLCY()+rng.nextInt(r.getHeight()-2)+1;
              xi=Floor.randFlip(rng);
            }
            if(!addPath(x,y,w,h,xi,yi)){
              tries++;
            }
            else{
              tries=lim;
              paths++;
              fails=0;
            }
          }
          tries=0;
          fails++;
      }
  }
  public void addEntrance(){
    entranceX = rooms.get(0).getTLCX()+rng.nextInt(rooms.get(0).getWidth()-2)+1;
    entranceY = rooms.get(0).getTLCY()+rng.nextInt(rooms.get(0).getHeight()-2)+1;
    grid[entranceY][entranceX]='~';
  }
  public void addExit(){
    exitX = rooms.get(1).getTLCX()+rng.nextInt(rooms.get(1).getWidth()-2)+1;
    exitY = rooms.get(1).getTLCY()+rng.nextInt(rooms.get(1).getHeight()-2)+1;
    grid[exitY][exitX]='*';
  }
  public void addAllRooms(){
    int tries=0;
    int fails=0;
    while(fails<grid.length*grid[0].length){
      Room r = new Room(rng.nextInt(grid[0].length/2)+6,
                        rng.nextInt(grid.length/2)+6);
      while(tries<grid.length*grid[0].length){
        int x=rng.nextInt(grid[0].length);
        int y=rng.nextInt(grid.length);
        if(!addRoom(r,x,y)){
          tries++;
        }
        else{
          r.setTLCX(x);
          r.setTLCY(y);
          rooms.add(r);
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
