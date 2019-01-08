import java.util.List;

public class Player extends Creature{

  private List<Equipment> equipment;
  private List<Item> inventory;
  private int experience;
	private int nextLvl;
	private int level;

	public Player(){
		equipment.clear();
		inventory.clear();
		experience = 0;
    level = 1;
    nextLvl = level * 100;
	}

  public boolean useConsumable(Consumable selected) {
    List<Integer> newStatsToAdd = selected.getStats();
    return false;
  }

  public boolean equip(Equipment selected){
    return false;
  }

  public boolean pickUp(){
    return false;
  }

	public void levelUp() { //need to run this constantly?
		if (experience > nextLvl) {
			level++;
      nextLvl = level * 100;
		}
	}


}
