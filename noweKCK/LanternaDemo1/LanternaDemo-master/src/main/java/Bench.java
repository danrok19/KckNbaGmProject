
public class Bench {

	private Player player1;
	private Player player2;
	private Player player3;

	public Bench(Player _player1, Player _player2, Player _player3) {
		this.player1 = _player1;
		this.player2 = _player2;
		this.player3 = _player3;
		
	}
	public void changePlayer(Player playerOut, Player playerIn) {
		if(this.player1 == playerIn) {
			this.player1 = playerOut;
		}
		else if(this.player2 == playerIn) {
			this.player2 = playerOut;
		}
		else if(this.player3 == playerIn) {
			this.player3 = playerOut;
		}
	}
	
	public Player getPlayer1() {
		return this.player1;
	}
	public Player getPlayer2() {
		return this.player2;
	}public Player getPlayer3() {
		return this.player3;
	}
	
	public void addCondition() {
		player1.addCondition();
		player2.addCondition();
		player3.addCondition();
	}

}
