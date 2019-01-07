import java.util.List;

public class Floor{
  private List<Room> rooms;
  //private List<Path> paths;
  private int entranceX;
  private int entranceY;
  private int exitX;
  private int exitY;
  //private char[][] grid;
  Floor(List<Room> _rooms, int enX, int enY, int exX, int exY){
    rooms = _rooms;
    entranceX = enX;
    entranceY = enY;
    exitX = exX;
    exitY = exY;
  }
  Floor(int depth){

  }
}
