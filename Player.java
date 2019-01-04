import java.util.List;

public class Player extends Creature{

  private List<Equipment> equipment;
  private List<Item> inventory;
  private int experience;

  public boolean useConsumable(Consumable selected) {
    return false;
  }

  public boolean equip(Equipment selected){
    return false;
  }

  public boolean pickUp(){
    return false;
  }
}
