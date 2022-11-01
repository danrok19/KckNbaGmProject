import com.googlecode.lanterna.gui2.BasicWindow;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

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
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


public class Change{

	private MultiWindowTextGUI gui;
	private Team team;
	private Squad squad;
	private Player playerIn;
	private Player playerOut;
	private Bench bench;
	
	public Change(MultiWindowTextGUI _gui, Team _team, Squad _squad, Bench _bench) {
		this.gui = _gui;
		this.team = _team;
		this.squad = _squad;
		this.bench = _bench;
		BasicWindow openWindow = new BasicWindow("---------ZMIANY-----------");
		//openWindow.setSize(new TerminalSize(80,30));
		
		
		
		//---Lewy Panel--------------------------------------------------------------------------------------------------
		Panel leftPanel = new Panel(new GridLayout(2));
		GridLayout gridLayout2 = (GridLayout)leftPanel.getLayoutManager();
		gridLayout2.setHorizontalSpacing(1);
		Label playerInLabel = new Label("Wchodzi:" + "XXXX");
		
		
		Panel buttonPanel = new Panel();
		buttonPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Button player0Button = new Button("Wchodzi", new Runnable() {
			@Override
			public void run() {
				//playerIn = team.getPlayers().get(0);
				playerIn = bench.getPlayer1();
				playerInLabel.setText("Wchodzi: "+ playerIn.getSurname());
			}
		});
		buttonPanel.addComponent(player0Button);
		Button player1Button = new Button("Wchodzi", new Runnable() {
			@Override
			public void run() {
				playerIn = bench.getPlayer2();
				playerInLabel.setText("Wchodzi: "+ playerIn.getSurname());
			}
		});
		buttonPanel.addComponent(player1Button);
		Button player2Button = new Button("Wchodzi", new Runnable() {
			@Override
			public void run() {
				//playerIn = team.getPlayers().get(2);
				playerIn = bench.getPlayer3();
				playerInLabel.setText("Wchodzi: "+ playerIn.getSurname());
			}
		});
		buttonPanel.addComponent(player2Button);
		
		
		

		
		Panel teamPanel = new Panel();
		teamPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Label playerLabel1 = null;
		Label playerLabel2= null;
		Label playerLabel3= null;
		Label playerLabel4= null;
		Label playerLabel5= null;
		Label playerLabel6= null;
		Label playerLabel7= null;
		Label playerLabel8= null;
		
		playerLabel1 = new Label(bench.getPlayer1().getName() + " " + bench.getPlayer1().getSurname() + " " + bench.getPlayer1().getCondition());
		playerLabel2 = new Label(bench.getPlayer2().getName() + " " + bench.getPlayer2().getSurname() + " " + bench.getPlayer2().getCondition());
		playerLabel3 = new Label(bench.getPlayer3().getName() + " " + bench.getPlayer3().getSurname() + " " + bench.getPlayer3().getCondition());
		
		teamPanel.addComponent(playerLabel1);
		teamPanel.addComponent(playerLabel2);
		teamPanel.addComponent(playerLabel3);
		
		
		leftPanel.addComponent(teamPanel);
		leftPanel.addComponent(buttonPanel);
		
		
		//---Prawy panel------------------------------------------------------------------------------------------------------------
		Panel rightPanel = new Panel(new GridLayout(2));
		GridLayout gridLayout3 = (GridLayout)rightPanel.getLayoutManager();
		gridLayout3.setHorizontalSpacing(1);
		Label playerOutLabel = new Label("Zschodzi:" + "XXXX");
		
		
		Panel buttonPanel2 = new Panel();
		buttonPanel2.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Button squad1Button = new Button("Zschodzi", new Runnable() {
			@Override
			public void run() {
				playerOut = squad.getPlayer1();
				playerOutLabel.setText("Zschodzi: "+ playerOut.getSurname());
			}
		});
		buttonPanel2.addComponent(squad1Button);
		Button squad2Button = new Button("Zschodzi", new Runnable() {
			@Override
			public void run() {
				playerOut = squad.getPlayer2();
				playerOutLabel.setText("Zschodzi: "+ playerOut.getSurname());
			}
		});
		buttonPanel2.addComponent(squad2Button);
		Button squad3Button = new Button("Zschodzi", new Runnable() {
			@Override
			public void run() {
				playerOut = squad.getPlayer3();
				playerOutLabel.setText("Zschodzi: "+ playerOut.getSurname());
			}
		});
		buttonPanel2.addComponent(squad3Button);
		Button squad4Button = new Button("Zschodzi", new Runnable() {
			@Override
			public void run() {
				playerOut = squad.getPlayer4();
				playerOutLabel.setText("Zschodzi: "+ playerOut.getSurname());
			}
		});
		buttonPanel2.addComponent(squad4Button);
		Button squad5Button = new Button("Zschodzi", new Runnable() {
			@Override
			public void run() {
				playerOut = squad.getPlayer5();
				playerOutLabel.setText("Zschodzi: "+ playerOut.getSurname());
			}
		});
		buttonPanel2.addComponent(squad5Button);
		
		
		
		
		Panel squadPanel = new Panel();
		squadPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Label squadLabel1 = null;
		Label squadLabel2= null;
		Label squadLabel3= null;
		Label squadLabel4= null;
		Label squadLabel5= null;
		squadLabel1 = new Label(squad.getPlayer1().getSurname() + " " + squad.getPlayer1().getCondition());
		squadLabel2 = new Label(squad.getPlayer2().getSurname() + " " + squad.getPlayer2().getCondition());
		squadLabel3 = new Label(squad.getPlayer3().getSurname() + " " + squad.getPlayer3().getCondition());
		squadLabel4 = new Label(squad.getPlayer4().getSurname() + " " + squad.getPlayer4().getCondition());
		squadLabel5 = new Label(squad.getPlayer5().getSurname() + " " + squad.getPlayer5().getCondition());
		
		squadPanel.addComponent(squadLabel1);
		squadPanel.addComponent(squadLabel2);
		squadPanel.addComponent(squadLabel3);
		squadPanel.addComponent(squadLabel4);
		squadPanel.addComponent(squadLabel5);
		
		rightPanel.addComponent(buttonPanel2);
		rightPanel.addComponent(squadPanel);
		
		
		
		
		// middlePanel--------------------------------------------------------------------------------
		
		Panel middlePanel = new Panel();
		middlePanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Button myChangeButton = new Button("Potwierdz", new Runnable() {
			@Override
			public void run() {
				if (playerIn == null || playerOut == null) {

				} else {
					squad.changePlayer(playerIn, playerOut);
					bench.changePlayer(playerOut, playerIn);
				}
			}
		});
		middlePanel.addComponent(playerInLabel);
		middlePanel.addComponent(playerOutLabel);
		middlePanel.addComponent(myChangeButton);
		
		Button ExitButton = new Button("Koniec Zmian", new Runnable() {
			@Override
			public void run() {
				openWindow.close();
			}
		});
		middlePanel.addComponent(ExitButton);
		
		
		//Glowny Panel---------------------------------------------------------------------------------
		Panel allPanel = new Panel(new GridLayout(3));
		GridLayout gridLayout1 = (GridLayout)allPanel.getLayoutManager();
		gridLayout1.setHorizontalSpacing(2);
		
		allPanel.addComponent(leftPanel);
		allPanel.addComponent(middlePanel);
		allPanel.addComponent(rightPanel);
		
		
		openWindow.setComponent(allPanel);
		
		gui.addWindowAndWait(openWindow);
	}

	
}
