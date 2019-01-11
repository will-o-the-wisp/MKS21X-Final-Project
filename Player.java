import java.util.List;
import java.util.ArrayList;

public class Player extends Creature{

  private List<Equipment> equipment;
  private List<Item> inventory;
  private List<Integer> experiences; //current exp, current lvl, needed exp

	public Player(){
		equipment.clear();
		inventory.clear();
		experiences.add(0); //not sure how add all works with collection
    experiences.add(1);
    experiences.add(100);
	}

  public boolean useConsumable(Consumable selected) {
    List<Integer> newStatsToAdd = new ArrayList<Integer>();
    newStatsToAdd.add(selected.getHP());
    newStatsToAdd.add(selected.getATK());
    newStatsToAdd.add(selected.getDEF());
    //duration
    return false;
  }

  public boolean equip(Equipment selected){
    return false;
  }

  public boolean pickUp(){
    return false;
  }

	public void levelUp() { //need to run this constantly?
		if (experiences.get(0) > experiences.get(2)) {
			experiences.set(1,experiences.get(1) + 1);
      experiences.set(2,experiences.get(1) * 100);
		}
	}

  public void setEquips(List<Equipment> gear) {
    equipment = gear;
  }

  public List<Equipment> getEquips() {
    return equipment;
  }

  public void setInventory(List<Item> itemBag){
    inventory = itemBag;
  }

  public List<Item> getInventory(){
    return inventory;
  }

  public void setExp(List<Integer> exp){
    experiences = exp;
  }

  public List<Integer> getExp() {
    return experiences;
  }
}
