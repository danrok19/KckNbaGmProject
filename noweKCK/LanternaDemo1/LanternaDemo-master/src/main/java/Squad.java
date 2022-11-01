
public class Squad {
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private Player player5;
	private int overall;
	
	public Squad(Player _player1,Player _player2, Player _player3, Player _player4, Player _player5) {
		this.player1 = _player1;
		this.player2 = _player2;
		this.player3 = _player3;
		this.player4 = _player4;
		this.player5 = _player5;
		this.overall = player1.getAttackOvr()+player1.getCondition()+player1.getDefenseOvr() +
				player2.getAttackOvr()+player2.getCondition()+player2.getDefenseOvr() +
				player3.getAttackOvr()+player3.getCondition()+player3.getDefenseOvr() +
				player4.getAttackOvr()+player4.getCondition()+player4.getDefenseOvr() +
				player5.getAttackOvr()+player5.getCondition()+player5.getDefenseOvr();
	}
	public int getOverall() {
		return this.overall;
	}
	
	public void updateOverall() {
		this.overall = player1.getAttackOvr()+player1.getCondition()+player1.getDefenseOvr() +
				player2.getAttackOvr()+player2.getCondition()+player2.getDefenseOvr() +
				player3.getAttackOvr()+player3.getCondition()+player3.getDefenseOvr() +
				player4.getAttackOvr()+player4.getCondition()+player4.getDefenseOvr() +
				player5.getAttackOvr()+player5.getCondition()+player5.getDefenseOvr();
	}
	public void changePlayer(Player playerIn, Player playerOut) {
		if(this.player1 == playerOut) {
			this.player1 = playerIn;
		}
		else if(this.player2 == playerOut) {
			this.player2 = playerIn;
		}
		else if(this.player3 == playerOut) {
			this.player3 = playerIn;
		}
		else if(this.player4 == playerOut) {
			this.player4 = playerIn;
		}
		else if(this.player5 == playerOut) {
			this.player5 = playerIn;
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
	public Player getPlayer4() {
		return this.player4;
	}
	public Player getPlayer5() {
		return this.player5;
	}

}
