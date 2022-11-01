import com.googlecode.lanterna.gui2.AbsoluteLayout;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.BorderLayout;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Action;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


public class Game {
	private MultiWindowTextGUI gui;
	private String myTeam;
	private String oppTeam;
	private Team myTeamT;
	private Team oppTeamT;
	private int myScore=0;
	private int oppScore=0;
	private int qtr=1;
	private int minute = 12;
	private Squad mySquad;
	private Squad oppSquad;
	private Bench myBench;
	private Bench oppBench;
	private History historia;
	private int licznik = 0;
	
	public Game(MultiWindowTextGUI _gui, String _myTeam, String _oppTeam, History _historia) {
		this.gui = _gui;
		this.myTeam = _myTeam;
		this.oppTeam = _oppTeam;
		this.historia = _historia;

		
		
		BasicWindow openWindow = new BasicWindow("Game");
		openWindow.setSize(new TerminalSize(80,30));
		
		

		//Glowny Panel -->GamePanel (Vertical)-------------------------------------------------------------------------
		Panel gamePanel = new Panel();
		gamePanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		
		//1 podrzedny panel -->mainPanel (Horizontal)--------------------------------------------------
		Panel mainPanel = new Panel();
		mainPanel.setLayoutManager(new GridLayout(2));
		GridLayout gridLayoutM = (GridLayout)mainPanel.getLayoutManager();
		gridLayoutM.setHorizontalSpacing(30);
		
		
		Panel myTeamPanel = new Panel();
		myTeamPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		myTeamPanel.addComponent(new EmptySpace());
		
		Panel oppTeamPanel = new Panel();
		oppTeamPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		oppTeamPanel.addComponent(new EmptySpace());
		
		//2 podrzedny panel -->SquadPanel (Horizontal)--------------------------------------------------
		Panel SquadPanel = new Panel(new GridLayout(3));
		GridLayout gridLayout = (GridLayout)SquadPanel.getLayoutManager();
		gridLayout.setHorizontalSpacing(22);
		
		//podPodprzedny panel -->scorePanel(Vertical)------------------------
		Panel scorePanel = new Panel();
		scorePanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Panel mySquadPanel = new Panel();
		mySquadPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Panel oppSquadPanel = new Panel();
		oppSquadPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Panel changePanel = new Panel(new GridLayout(2));
		GridLayout gridLayout1 = (GridLayout)changePanel.getLayoutManager();
		gridLayout1.setHorizontalSpacing(5);
		
		
		//Tablica wyniku-----------------------------------------------------------
		Label scoreLabel = new Label("        "+myScore + " : " +oppScore);
		scoreLabel.addStyle(SGR.BOLD);
		Label timeLabel = new Label("   Qtr: "+qtr+" Minute: "+minute);
		timeLabel.addStyle(SGR.BOLD);
		scorePanel.addComponent(scoreLabel);
		scorePanel.addComponent(timeLabel);
		
		
		
		Button myChangeButton = new Button("Zmiana", new Runnable() {
			@Override
			public void run() {
				 new Change(gui, myTeamT, mySquad, myBench);
			}
		});
		
		Button oppChangeButton = new Button("Zmiana", new Runnable() {
			@Override
			public void run() {
				 new Change(gui, oppTeamT, oppSquad, oppBench);
			}
		});
		
		changePanel.addComponent(myChangeButton);
		changePanel.addComponent(oppChangeButton);
		
		scorePanel.addComponent(changePanel);
		//Tablica wyniku-----------------------------------------------------------

		//USTALANIE DRUZYNY GOSPODARZA
		if(myTeam == "Brooklyn Nets") {
			myTeamT = new Team("Brooklyn Nets");
			
		}
		else if(myTeam == "Boston Celtics"){
			myTeamT = new Team("Boston Celtics");
		}
		else if(myTeam == "Toronto Raptors"){
			myTeamT = new Team("Toronto Raptors");
		}
		else if(myTeam == "Philadelphia 76ers"){
			myTeamT = new Team("Philadelphia 76ers");
		}
		else if(myTeam == "New York Knicks"){
			myTeamT = new Team("New York Knicks");
		}
		Label playerLabel1 = new Label("("+myTeamT.getPlayers().get(0).getAttackOvr()+", "+myTeamT.getPlayers().get(0).getDefenseOvr()+")"+myTeamT.getPlayers().get(0).getName() + " " + myTeamT.getPlayers().get(0).getSurname()
				+ ": " + myTeamT.getPlayers().get(0).getPoints()+" pkt.");
		Label playerLabel2 = new Label("("+myTeamT.getPlayers().get(1).getAttackOvr()+", "+myTeamT.getPlayers().get(1).getDefenseOvr()+")"+myTeamT.getPlayers().get(1).getName() + " " + myTeamT.getPlayers().get(1).getSurname()
				+ ": " + myTeamT.getPlayers().get(1).getPoints()+" pkt.");
		Label playerLabel3 = new Label("("+myTeamT.getPlayers().get(2).getAttackOvr()+", "+myTeamT.getPlayers().get(2).getDefenseOvr()+")"+myTeamT.getPlayers().get(2).getName() + " " + myTeamT.getPlayers().get(2).getSurname()
				+ ": " + myTeamT.getPlayers().get(2).getPoints()+" pkt.");
		Label playerLabel4 = new Label("("+myTeamT.getPlayers().get(3).getAttackOvr()+", "+myTeamT.getPlayers().get(3).getDefenseOvr()+")"+myTeamT.getPlayers().get(3).getName() + " " + myTeamT.getPlayers().get(3).getSurname()
				+ ": " + myTeamT.getPlayers().get(3).getPoints()+" pkt.");
		Label playerLabel5 = new Label("("+myTeamT.getPlayers().get(4).getAttackOvr()+", "+myTeamT.getPlayers().get(4).getDefenseOvr()+")"+myTeamT.getPlayers().get(4).getName() + " " + myTeamT.getPlayers().get(4).getSurname()
				+ ": " + myTeamT.getPlayers().get(4).getPoints()+" pkt.");
		Label playerLabel6 = new Label("("+myTeamT.getPlayers().get(5).getAttackOvr()+", "+myTeamT.getPlayers().get(5).getDefenseOvr()+")"+myTeamT.getPlayers().get(5).getName() + " " + myTeamT.getPlayers().get(5).getSurname()
				+ ": " + myTeamT.getPlayers().get(5).getPoints()+" pkt.");
		Label playerLabel7 = new Label("("+myTeamT.getPlayers().get(6).getAttackOvr()+", "+myTeamT.getPlayers().get(6).getDefenseOvr()+")"+myTeamT.getPlayers().get(6).getName() + " " + myTeamT.getPlayers().get(6).getSurname()
				+ ": " + myTeamT.getPlayers().get(6).getPoints()+" pkt.");
		Label playerLabel8 = new Label("("+myTeamT.getPlayers().get(7).getAttackOvr()+", "+myTeamT.getPlayers().get(7).getDefenseOvr()+")"+myTeamT.getPlayers().get(7).getName() + " " + myTeamT.getPlayers().get(7).getSurname()
				+ ": " + myTeamT.getPlayers().get(7).getPoints()+" pkt.");
		myTeamPanel.addComponent(playerLabel1);
		myTeamPanel.addComponent(playerLabel2);
		myTeamPanel.addComponent(playerLabel3);
		myTeamPanel.addComponent(playerLabel4);
		myTeamPanel.addComponent(playerLabel5);
		myTeamPanel.addComponent(playerLabel6);
		myTeamPanel.addComponent(playerLabel7);
		myTeamPanel.addComponent(playerLabel8);
		


		//USTALANIE DRUZYNY PRZECIWNIKA
		if(oppTeam == "Brooklyn Nets") {
			oppTeamT = new Team("Brooklyn Nets");
		}
		else if(oppTeam == "Boston Celtics"){
			oppTeamT = new Team("Boston Celtics");
		}
		else if(oppTeam == "Toronto Raptors"){
			oppTeamT = new Team("Toronto Raptors");
		}
		else if(oppTeam == "Philadelphia 76ers"){
			oppTeamT = new Team("Philadelphia 76ers");
		}
		else if(oppTeam == "New York Knicks"){
			oppTeamT = new Team("New York Knicks");
		}
			
		Label playerLabel01 = new Label("("+oppTeamT.getPlayers().get(0).getAttackOvr()+", "+oppTeamT.getPlayers().get(0).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(0).getName() + " " + oppTeamT.getPlayers().get(0).getSurname()+ ": " + oppTeamT.getPlayers().get(0).getPoints()+" pkt.");
		Label playerLabel02 = new Label("("+oppTeamT.getPlayers().get(1).getAttackOvr()+", "+oppTeamT.getPlayers().get(1).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(1).getName() + " " + oppTeamT.getPlayers().get(1).getSurname()+ ": " + oppTeamT.getPlayers().get(1).getPoints()+" pkt.");
		Label playerLabel03 = new Label("("+oppTeamT.getPlayers().get(2).getAttackOvr()+", "+oppTeamT.getPlayers().get(2).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(2).getName() + " " + oppTeamT.getPlayers().get(2).getSurname()+ ": " + oppTeamT.getPlayers().get(2).getPoints()+" pkt.");
		Label playerLabel04 = new Label("("+oppTeamT.getPlayers().get(3).getAttackOvr()+", "+oppTeamT.getPlayers().get(3).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(3).getName() + " " + oppTeamT.getPlayers().get(3).getSurname()+ ": " + oppTeamT.getPlayers().get(3).getPoints()+" pkt.");
		Label playerLabel05 = new Label("("+oppTeamT.getPlayers().get(4).getAttackOvr()+", "+oppTeamT.getPlayers().get(4).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(4).getName() + " " + oppTeamT.getPlayers().get(4).getSurname()+ ": " + oppTeamT.getPlayers().get(4).getPoints()+" pkt.");
		Label playerLabel06 = new Label("("+oppTeamT.getPlayers().get(5).getAttackOvr()+", "+oppTeamT.getPlayers().get(5).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(5).getName() + " " + oppTeamT.getPlayers().get(5).getSurname()+ ": " + oppTeamT.getPlayers().get(5).getPoints()+" pkt.");
		Label playerLabel07 = new Label("("+oppTeamT.getPlayers().get(6).getAttackOvr()+", "+oppTeamT.getPlayers().get(6).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(6).getName() + " " + oppTeamT.getPlayers().get(6).getSurname()+ ": " + oppTeamT.getPlayers().get(6).getPoints()+" pkt.");
		Label playerLabel08 = new Label("("+oppTeamT.getPlayers().get(7).getAttackOvr()+", "+oppTeamT.getPlayers().get(7).getDefenseOvr()+")"+
				oppTeamT.getPlayers().get(7).getName() + " " + oppTeamT.getPlayers().get(7).getSurname()+ ": " + oppTeamT.getPlayers().get(7).getPoints()+" pkt.");

		oppTeamPanel.addComponent(playerLabel01);
		oppTeamPanel.addComponent(playerLabel02);
		oppTeamPanel.addComponent(playerLabel03);
		oppTeamPanel.addComponent(playerLabel04);
		oppTeamPanel.addComponent(playerLabel05);
		oppTeamPanel.addComponent(playerLabel06);
		oppTeamPanel.addComponent(playerLabel07);
		oppTeamPanel.addComponent(playerLabel08);
		
		
		
		//USTALANIE WYJSCIOWYCH PIATEK I ŁAWKI--------------
		this.mySquad = new Squad(myTeamT.getPlayers().get(0),myTeamT.getPlayers().get(1),myTeamT.getPlayers().get(2),myTeamT.getPlayers().get(3),myTeamT.getPlayers().get(4));
		this.oppSquad = new Squad(oppTeamT.getPlayers().get(0),oppTeamT.getPlayers().get(1),oppTeamT.getPlayers().get(2),oppTeamT.getPlayers().get(3),oppTeamT.getPlayers().get(4));
		this.myBench = new Bench(myTeamT.getPlayers().get(5),myTeamT.getPlayers().get(6),myTeamT.getPlayers().get(7));
		this.oppBench = new Bench(oppTeamT.getPlayers().get(5),oppTeamT.getPlayers().get(6),oppTeamT.getPlayers().get(7));
		
		
		Label myNameTeam = new Label(myTeamT.getName());
		myNameTeam.setBackgroundColor(new TextColor.RGB(0,255,0));
		Label myPlayer1 = new Label(mySquad.getPlayer1().getSurname()+mySquad.getPlayer1().getCondition());
		Label myPlayer2 = new Label(mySquad.getPlayer2().getSurname()+mySquad.getPlayer2().getCondition());
		Label myPlayer3 = new Label(mySquad.getPlayer3().getSurname()+mySquad.getPlayer3().getCondition());
		Label myPlayer4 = new Label(mySquad.getPlayer4().getSurname()+mySquad.getPlayer4().getCondition());
		Label myPlayer5 = new Label(mySquad.getPlayer5().getSurname()+mySquad.getPlayer5().getCondition());
		myPlayer1.addStyle(SGR.BOLD);
		myPlayer2.addStyle(SGR.BOLD);
		myPlayer3.addStyle(SGR.BOLD);
		myPlayer4.addStyle(SGR.BOLD);
		myPlayer5.addStyle(SGR.BOLD);
		Label starting1 = new Label(Symbols.DIAMOND+"Line-up"+Symbols.DIAMOND);
		starting1.addStyle(SGR.BOLD);
		
		mySquadPanel.addComponent(myNameTeam);
		mySquadPanel.addComponent(starting1);
		mySquadPanel.addComponent(myPlayer1);
		mySquadPanel.addComponent(myPlayer2);
		mySquadPanel.addComponent(myPlayer3);
		mySquadPanel.addComponent(myPlayer4);
		mySquadPanel.addComponent(myPlayer5);
		
		
		
		Label oppNameTeam = new Label(oppTeamT.getName());
		oppNameTeam.setBackgroundColor(new TextColor.RGB(255,0,0));
		Label oppPlayer1 = new Label(oppSquad.getPlayer1().getSurname()+oppSquad.getPlayer1().getCondition());
		Label oppPlayer2 = new Label(oppSquad.getPlayer2().getSurname()+oppSquad.getPlayer2().getCondition());
		Label oppPlayer3 = new Label(oppSquad.getPlayer3().getSurname()+oppSquad.getPlayer3().getCondition());
		Label oppPlayer4 = new Label(oppSquad.getPlayer4().getSurname()+oppSquad.getPlayer4().getCondition());
		Label oppPlayer5 = new Label(oppSquad.getPlayer5().getSurname()+oppSquad.getPlayer5().getCondition());
		oppPlayer1.addStyle(SGR.BOLD);
		oppPlayer2.addStyle(SGR.BOLD);
		oppPlayer3.addStyle(SGR.BOLD);
		oppPlayer4.addStyle(SGR.BOLD);
		oppPlayer5.addStyle(SGR.BOLD);
		Label starting2 = new Label(Symbols.DIAMOND+"Line-up"+Symbols.DIAMOND);
		starting2.addStyle(SGR.BOLD);
		
		oppSquadPanel.addComponent(oppNameTeam);
		oppSquadPanel.addComponent(starting2);
		oppSquadPanel.addComponent(oppPlayer1);
		oppSquadPanel.addComponent(oppPlayer2);
		oppSquadPanel.addComponent(oppPlayer3);
		oppSquadPanel.addComponent(oppPlayer4);
		oppSquadPanel.addComponent(oppPlayer5);
		
		//-------------------------------------------
		
		Button minuteButton = new Button("-----Next Minute-----", new Runnable() {
			@Override
			public void run() {
				if(minute == 0 && qtr < 4) {
					qtr++;
					minute = 12;
				}
				else if(minute == 0 && qtr == 4) {
					timeLabel.setText("      !!!KONIEC!!!");
					licznik++;
					if(licznik < 2) {
						historia.addToHistory(_myTeam, _oppTeam, myScore, oppScore);
					}
						
					
				}
				else {
					minute--;
					timeLabel.setText("   Qtr: "+qtr+" Minute: "+minute);
					mySquad.updateOverall();
					oppSquad.updateOverall();
					myBench.addCondition();
					oppBench.addCondition();
					
					//Aktualizacja skladu na boisku
					myPlayer1.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+mySquad.getPlayer1().getSurname()+":"+mySquad.getPlayer1().getCondition());
					myPlayer2.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+mySquad.getPlayer2().getSurname()+":"+mySquad.getPlayer2().getCondition());
					myPlayer3.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+mySquad.getPlayer3().getSurname()+":"+mySquad.getPlayer3().getCondition());
					myPlayer4.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+mySquad.getPlayer4().getSurname()+":"+mySquad.getPlayer4().getCondition());
					myPlayer5.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+mySquad.getPlayer5().getSurname()+":"+mySquad.getPlayer5().getCondition());
					
					oppPlayer1.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+oppSquad.getPlayer1().getSurname()+":"+oppSquad.getPlayer1().getCondition());
					oppPlayer2.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+oppSquad.getPlayer2().getSurname()+":"+oppSquad.getPlayer2().getCondition());
					oppPlayer3.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+oppSquad.getPlayer3().getSurname()+":"+oppSquad.getPlayer3().getCondition());
					oppPlayer4.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+oppSquad.getPlayer4().getSurname()+":"+oppSquad.getPlayer4().getCondition());
					oppPlayer5.setText(Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK+oppSquad.getPlayer5().getSurname()+":"+oppSquad.getPlayer5().getCondition());
					//-------------------------------
					
					Random rand = new Random();
					int mySquadPower = mySquad.getOverall();
					int oppSquadPower = oppSquad.getOverall();
					int difference = mySquadPower - oppSquadPower;
					if(difference > 20 ) {
						difference = 20;
					}
					int howMany = rand.nextInt(3)+2;
					for(int i = 0; i < howMany ; i++) {
						Matchup matchup;
						int chance = rand.nextInt(100) + 1;
						Player player1;
						Player player2;
						int myMatchPlayer = rand.nextInt(5) + 1;
						switch (myMatchPlayer) {
						case 1:
							player1 = mySquad.getPlayer1();
							break;
						case 2:
							player1 = mySquad.getPlayer2();
							break;
						case 3:
							player1 = mySquad.getPlayer3();
							break;
						case 4:
							player1 = mySquad.getPlayer4();
							break;
						case 5:
							player1 = mySquad.getPlayer5();
							break;
						default:
							player1 = mySquad.getPlayer1();
						}
						int oppMatchPlayer = rand.nextInt(5) + 1;
						switch (oppMatchPlayer) {
						case 1:
							player2 = oppSquad.getPlayer1();
							break;
						case 2:
							player2 = oppSquad.getPlayer2();
							break;
						case 3:
							player2 = oppSquad.getPlayer3();
							break;
						case 4:
							player2 = oppSquad.getPlayer4();
							break;
						case 5:
							player2 = oppSquad.getPlayer5();
							break;
						default:
							player2 = oppSquad.getPlayer1();
						}
						if (chance < 50 + difference) {
							matchup = new Matchup(player1, player2);
							myScore += matchup.getPoints();
							
						} else {
							matchup = new Matchup(player2, player1);
							oppScore += matchup.getPoints();
						}
						
					}
					//aktualizacja tablicy i skladow
					scoreLabel.setText("        "+myScore + " : " +oppScore);
					if(myScore > oppScore) {
						scoreLabel.setForegroundColor(new TextColor.RGB(0,255,0));
						scoreLabel.setText("        "+myScore + Symbols.TRIANGLE_RIGHT_POINTING_MEDIUM_BLACK +oppScore);
						
					}
					else if(myScore < oppScore) {
						scoreLabel.setForegroundColor(new TextColor.RGB(255,0,0));
						scoreLabel.setText("        "+myScore + Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK +oppScore);
					}
					playerLabel1.setText("("+myTeamT.getPlayers().get(0).getAttackOvr()+", "+myTeamT.getPlayers().get(0).getDefenseOvr()+")"+myTeamT.getPlayers().get(0).getName() + " " + myTeamT.getPlayers().get(0).getSurname() + ": " + myTeamT.getPlayers().get(0).getPoints()+" pkt.");
					playerLabel2.setText("("+myTeamT.getPlayers().get(1).getAttackOvr()+", "+myTeamT.getPlayers().get(1).getDefenseOvr()+")"+myTeamT.getPlayers().get(1).getName() + " " + myTeamT.getPlayers().get(1).getSurname() + ": " + myTeamT.getPlayers().get(1).getPoints()+" pkt.");
					playerLabel3.setText("("+myTeamT.getPlayers().get(2).getAttackOvr()+", "+myTeamT.getPlayers().get(2).getDefenseOvr()+")"+myTeamT.getPlayers().get(2).getName() + " " + myTeamT.getPlayers().get(2).getSurname() + ": " + myTeamT.getPlayers().get(2).getPoints()+" pkt.");
					playerLabel4.setText("("+myTeamT.getPlayers().get(3).getAttackOvr()+", "+myTeamT.getPlayers().get(3).getDefenseOvr()+")"+myTeamT.getPlayers().get(3).getName() + " " + myTeamT.getPlayers().get(3).getSurname() + ": " + myTeamT.getPlayers().get(3).getPoints()+" pkt.");
					playerLabel5.setText("("+myTeamT.getPlayers().get(4).getAttackOvr()+", "+myTeamT.getPlayers().get(4).getDefenseOvr()+")"+myTeamT.getPlayers().get(4).getName() + " " + myTeamT.getPlayers().get(4).getSurname() + ": " + myTeamT.getPlayers().get(4).getPoints()+" pkt.");
					playerLabel6.setText("("+myTeamT.getPlayers().get(5).getAttackOvr()+", "+myTeamT.getPlayers().get(5).getDefenseOvr()+")"+myTeamT.getPlayers().get(5).getName() + " " + myTeamT.getPlayers().get(5).getSurname() + ": " + myTeamT.getPlayers().get(5).getPoints()+" pkt.");
					playerLabel7.setText("("+myTeamT.getPlayers().get(6).getAttackOvr()+", "+myTeamT.getPlayers().get(6).getDefenseOvr()+")"+myTeamT.getPlayers().get(6).getName() + " " + myTeamT.getPlayers().get(6).getSurname() + ": " + myTeamT.getPlayers().get(6).getPoints()+" pkt.");
					playerLabel8.setText("("+myTeamT.getPlayers().get(7).getAttackOvr()+", "+myTeamT.getPlayers().get(7).getDefenseOvr()+")"+myTeamT.getPlayers().get(7).getName() + " " + myTeamT.getPlayers().get(7).getSurname() + ": " + myTeamT.getPlayers().get(7).getPoints()+" pkt.");
					
					
					playerLabel01.setText("("+oppTeamT.getPlayers().get(0).getAttackOvr()+", "+oppTeamT.getPlayers().get(0).getDefenseOvr()+")"+oppTeamT.getPlayers().get(0).getName() + " " + oppTeamT.getPlayers().get(0).getSurname() + ": " + oppTeamT.getPlayers().get(0).getPoints()+" pkt.");
					playerLabel02.setText("("+oppTeamT.getPlayers().get(1).getAttackOvr()+", "+oppTeamT.getPlayers().get(1).getDefenseOvr()+")"+oppTeamT.getPlayers().get(1).getName() + " " + oppTeamT.getPlayers().get(1).getSurname() + ": " + oppTeamT.getPlayers().get(1).getPoints()+" pkt.");
					playerLabel03.setText("("+oppTeamT.getPlayers().get(2).getAttackOvr()+", "+oppTeamT.getPlayers().get(2).getDefenseOvr()+")"+oppTeamT.getPlayers().get(2).getName() + " " + oppTeamT.getPlayers().get(2).getSurname() + ": " + oppTeamT.getPlayers().get(2).getPoints()+" pkt.");
					playerLabel04.setText("("+oppTeamT.getPlayers().get(3).getAttackOvr()+", "+oppTeamT.getPlayers().get(3).getDefenseOvr()+")"+oppTeamT.getPlayers().get(3).getName() + " " + oppTeamT.getPlayers().get(3).getSurname() + ": " + oppTeamT.getPlayers().get(3).getPoints()+" pkt.");
					playerLabel05.setText("("+oppTeamT.getPlayers().get(4).getAttackOvr()+", "+oppTeamT.getPlayers().get(4).getDefenseOvr()+")"+oppTeamT.getPlayers().get(4).getName() + " " + oppTeamT.getPlayers().get(4).getSurname() + ": " + oppTeamT.getPlayers().get(4).getPoints()+" pkt.");
					playerLabel06.setText("("+oppTeamT.getPlayers().get(5).getAttackOvr()+", "+oppTeamT.getPlayers().get(5).getDefenseOvr()+")"+oppTeamT.getPlayers().get(5).getName() + " " + oppTeamT.getPlayers().get(5).getSurname() + ": " + oppTeamT.getPlayers().get(5).getPoints()+" pkt.");
					playerLabel07.setText("("+oppTeamT.getPlayers().get(6).getAttackOvr()+", "+oppTeamT.getPlayers().get(6).getDefenseOvr()+")"+oppTeamT.getPlayers().get(6).getName() + " " + oppTeamT.getPlayers().get(6).getSurname() + ": " + oppTeamT.getPlayers().get(6).getPoints()+" pkt.");
					playerLabel08.setText("("+oppTeamT.getPlayers().get(7).getAttackOvr()+", "+oppTeamT.getPlayers().get(7).getDefenseOvr()+")"+oppTeamT.getPlayers().get(7).getName() + " " + oppTeamT.getPlayers().get(7).getSurname() + ": " + oppTeamT.getPlayers().get(7).getPoints()+" pkt.");
					
				
				}
			}
		});
		scorePanel.addComponent(minuteButton);
		
		
		
		
		SquadPanel.addComponent(mySquadPanel);
		SquadPanel.addComponent(scorePanel.withBorder(Borders.doubleLine()));
		SquadPanel.addComponent(oppSquadPanel);
	
		
		
		mainPanel.addComponent(myTeamPanel.withBorder(Borders.doubleLine()));
		mainPanel.addComponent(oppTeamPanel.withBorder(Borders.doubleLine()));
		gamePanel.addComponent(SquadPanel);
		gamePanel.addComponent(mainPanel);
		
		
		Button exitButton = new Button("<<Wyjdz do Menu<<", new Runnable() {
			@Override
			public void run() {
				openWindow.close();
				new Menu(gui,historia);
			}
		});
		openWindow.setComponent(gamePanel);


		
		Drawings drawings = new Drawings();
		Label basket = drawings.getBasket();
		
		Panel thirdPanel = new Panel();
		thirdPanel.setLayoutManager(new GridLayout(2));

		Label spaceLabel = new Label("    ");

		thirdPanel.addComponent(spaceLabel);
		thirdPanel.addComponent(basket);

		
		gamePanel.addComponent(thirdPanel);
		gamePanel.addComponent(exitButton.withBorder(Borders.singleLine()));
		//gamePanel.addComponent(art);
		gui.addWindowAndWait(openWindow);
		
		
		
		
		
	}

}
