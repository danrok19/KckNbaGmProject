
public class Player {

	private String name;
	private String surname;
	private int attackOvr;
	private int defenseOvr;
	private int points = 0;
	private int fouls = 0;
	
	private int condition = 100;
	
	public Player(String _name, String _surname, int _attackOvr, int _defenseOvr) {
		this.name = _name;
		this.surname = _surname;
		this.attackOvr = _attackOvr;
		this.defenseOvr = _defenseOvr;
	}
	public String getName() {
		return this.name;
	}
	public String getSurname() {
		return this.surname;
	}
	public int getAttackOvr() {
		return this.attackOvr;
	}
	public int getDefenseOvr() {
		return this.defenseOvr;
	}
	public int getCondition() {
		return this.condition;
	}
	public void setCondition(int howMuch, boolean plusMinus) {
		if(plusMinus) {
			condition +=howMuch;
			if(condition > 100) {
				condition = 100;
			}
		}
		else {
			condition -= howMuch;
		}
	}
	public void addPoints(int _points) {
		this.points += _points;
	}
	
	public int getPoints() {
		return this.points;
	}
	public void addCondition() {
		if(this.condition<100) {
			condition+=9;
			if(this.condition>100) {
				this.condition = 100;
			}
		}
	}
	
}
