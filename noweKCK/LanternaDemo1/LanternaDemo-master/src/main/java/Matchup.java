import java.util.Random;

public class Matchup {
	private Player player1; //atakujacy
	private Player player2; //broniacy
	private int points = 0; //punkty zdobyte przez zawodnika atakujacego
	public Matchup(Player _player1, Player _player2) {
		this.player1 = _player1;
		this.player2 = _player2;
		
		int addition = player1.getAttackOvr() - player2.getDefenseOvr();
		Random rand = new Random();
		
		int chance = rand.nextInt(100)+1;
		//jezeli chance miesci sie w przedziale <1, 50 + addition> to atakujacy zdodywa punkty
		if(chance < (50 + addition)) {
			int twoOrThree = rand.nextInt(5)+1;
			if(twoOrThree == 5) {
				points = 4;
			}
			else if(twoOrThree == 4){
				points = 3;
			}
			else if(twoOrThree > 2) {
				points = 2;
			}
			else {
				points = 1;
			}
			//obnizamy stamine dla graczy
			disCondition(player1, 5,false);
			disCondition(player2, 10,false);
			player1.addPoints(points);
		}
		
		//jezeli nie to obronca wybronil akcje
		else {
			points = 0;
			//obnizamy stamine dla graczy
			disCondition(player2, 5,false);
			disCondition(player1, 10,false);
		}
	}
	
	
	public int getPoints() {
		return points;
	}
	
	private void disCondition(Player _player, int howMuch,boolean plusMinus) {
		_player.setCondition(howMuch, plusMinus);
	}

}
