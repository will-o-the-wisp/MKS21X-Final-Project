import java.util.List;
import java.util.ArrayList;

public class Interactive extends Entity{

  private List<Integer> stats = new ArrayList<Integer>();	//Health, Attack, Defense

	public Interactive(){				//Basic statline
		stats.add(10);				//will loop later
		stats.add(1);
		stats.add(1);
	}

	public Interactive(int health, int attack, int defense){
		stats.set(0,health);
		stats.set(1,attack);
		stats.set(2,defense);
	}

  public int getHP(){
    return stats.get(0);
  }

  public int getATK(){
    return stats.get(1);
  }

  public int getDEF() {
    return stats.get(2);
  }

  public List<Integer> getStats() {
    return stats;
  }

  public void setStats(List<Integer> newStat){
    stats = newStat;
  }

  public void setHP(int newStat){
    stats.set(0, newStat); //might need to manually set each one for this
  }

  public void setATK(int newStat) {
    stats.set(1, newStat);
  }

  public void setDEF(int newStat) {
    stats.set(2, newStat);
  }

  public void addStats(List<Integer> newStat) { //to the end for items/equips
    for (int x = 0; x < stats.size(); x++ ){
      stats.set(x,stats.get(x) + newStat.get(x));
    }
  }
}
