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
	}

  public boolean useConsumable(Consumable selected) {
    List<int> newStatsToAdd = selected.stats;
    return false;
  }

  public boolean equip(Equipment selected){
    return false;
  }

  public boolean pickUp(){
    return false;
  }

	public boolean levelUp(experience current) {
		if (current > nextLvl) {
			level++;
		}
	}
}
