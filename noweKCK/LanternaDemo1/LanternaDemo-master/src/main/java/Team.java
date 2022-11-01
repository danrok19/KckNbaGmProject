import java.util.ArrayList;
import java.util.List;

public class Team {

	private String name;
	private List<Player> playersList;
	
	public Team(String _name) {
		this.name = _name;
		if(name == "Brooklyn Nets") {
			playersList = new ArrayList<Player>();
			playersList.add(new Player("Kyrie", "Irving", 95, 83));
			playersList.add(new Player("Kevin", "Durant", 96, 85));
			playersList.add(new Player("Ben", "Simons", 80, 91));
			playersList.add(new Player("Patty", "Mills", 85, 78));
			playersList.add(new Player("Nic", "Claxton", 87, 89));
			playersList.add(new Player("Markieff", "Morris", 84, 88));
			playersList.add(new Player("Seth", "Curry", 90, 72));
			playersList.add(new Player("T.J.", "Warren", 85, 83));
		}
		else if(name == "Boston Celtics") {
			playersList = new ArrayList<Player>();
			playersList.add(new Player("Jason", "Tatum", 94, 81));
			playersList.add(new Player("Jaylen", "Brown", 92, 89));
			playersList.add(new Player("Grant", "Williams", 79, 91));
			playersList.add(new Player("Marcus", "Smart", 83, 93));
			playersList.add(new Player("Al", "Holford", 84, 89));
			playersList.add(new Player("Derrick", "White", 82, 79));
			playersList.add(new Player("Robert", "Williams", 84, 92));
			playersList.add(new Player("Malcolm", "Brogdon", 84, 80));
		}
		else if(name == "Toronto Raptors") {
			playersList = new ArrayList<Player>();
			playersList.add(new Player("Fred", "Vanvleet", 85, 80));
			playersList.add(new Player("Pascal", "Siakam", 92, 87));
			playersList.add(new Player("Scottie", "Barnes", 85, 89));
			playersList.add(new Player("O.G.", "Anunoby", 79, 81));
			playersList.add(new Player("Gary", "Trent Jr.", 85, 85));
			playersList.add(new Player("Juancho", "Hernangomez", 75, 75));
			playersList.add(new Player("Ron", "Harper Jr.", 81, 80));
			playersList.add(new Player("Otto", "Porter Jr", 79, 75));
		}
		else if(name == "Philadelphia 76ers") {
			playersList = new ArrayList<Player>();
			playersList.add(new Player("Joel", "Embiid", 94, 89));
			playersList.add(new Player("James", "Harden", 92, 80));
			playersList.add(new Player("Tobias", "Harris", 86, 86));
			playersList.add(new Player("PJ", "Tucker", 79, 86));
			playersList.add(new Player("Tyrese", "Maxey", 84, 83));
			playersList.add(new Player("Furkan", "Korkmaz", 75, 75));
			playersList.add(new Player("Matisse", "Thybulle", 76, 82));
			playersList.add(new Player("Montrezl", "Harrell", 82, 81));
		}
		
		else if(name == "New York Knicks") {
			playersList = new ArrayList<Player>();
			playersList.add(new Player("Derrick", "Rose", 90, 85));
			playersList.add(new Player("RJ", "Barrett", 89, 87));
			playersList.add(new Player("Mitchell", "Robinson", 85, 95));
			playersList.add(new Player("Julius", "Randle", 80, 88));
			playersList.add(new Player("Obi", "Toppin", 79, 78));
			playersList.add(new Player("Cam", "Reddish", 73, 73));
			playersList.add(new Player("Evan", "Fourier", 80, 79));
			playersList.add(new Player("Immanuel", "Quickley", 73, 71));
		}
	}
	
	public List<Player> getPlayers(){
		return playersList;
	}
	public String getName() {
		return name;
	}
	
}
