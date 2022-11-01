import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.AnimatedLabel;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Menu {

	private Screen screen;
	private MultiWindowTextGUI gui;
	private String myTeam = "";
	private String oppTeam = "";
	private History historia;
	private List<String> historyList = null;
	private int counterHistory = 0;
	
	public Menu(MultiWindowTextGUI _gui, History _historia) {

		this.gui = _gui;
		this.historia = _historia;
		if(historia.getHistory().isEmpty()) {
		}
		else {
			this.historyList = historia.getHistory();
		}

		BasicWindow openWindow = new BasicWindow("--------" + Symbols.TRIANGLE_DOWN_POINTING_BLACK
				+ "---------------------------Wybierz Druzyny-------------------------------"
				+ Symbols.TRIANGLE_DOWN_POINTING_BLACK + "-------------");

		// wszystkie panele w menu
		Panel mainPanel = new Panel();
		mainPanel.setLayoutManager(new GridLayout(3));
		GridLayout gridLayout2 = (GridLayout) mainPanel.getLayoutManager();
		gridLayout2.setHorizontalSpacing(15);

		Panel myPanel = new Panel();
		myPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

		Panel oppPanel = new Panel();
		oppPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

		Panel bigPanel = new Panel();
		bigPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

		Panel logoPanel = new Panel();
		logoPanel.withBorder(Borders.singleLine());
		logoPanel.setLayoutManager(new GridLayout(2));
		GridLayout gridLayout3 = (GridLayout) logoPanel.getLayoutManager();
		gridLayout3.setHorizontalSpacing(10);

		// labele
		Label myLabel = new Label(myTeam);
		myLabel.setBackgroundColor(new TextColor.RGB(0, 255, 0));
		Label oppLabel = new Label(oppTeam);
		oppLabel.setBackgroundColor(new TextColor.RGB(255, 0, 0));
		Label myInfoLabel = new Label("Twoja druzyna:");
		myInfoLabel.addStyle(SGR.BOLD);

		Label oppInfoLabel = new Label("Druzyna przeciwnika:");
		oppInfoLabel.addStyle(SGR.BOLD);

		Label myLogo = new Label("					");
		Label oppLogo = new Label("					");

		logoPanel.addComponent(myLogo.withBorder(Borders.singleLine()));
		logoPanel.addComponent(oppLogo.withBorder(Borders.singleLine()));

		Drawings drawing = new Drawings();

		// Wszystkie gudziki do klikania
		Button button = new Button("Rozpocznij", new Runnable() {
			@Override
			public void run() {
				if(!myTeam.isEmpty()) {
					openWindow.close();
					new Game(gui, myTeam, oppTeam,historia);
				}
			}

		});

		Button exitButton = new Button("<<Wyjdz do Logowania<<", new Runnable() {
			@Override
			public void run() {
				openWindow.close();
				new LogWindow(gui);
			}
		});

		// button do wyboru brooklyn
		// --opp--
		Button oppBrooklynButton = new Button("----Brooklyn Nets----", new Runnable() {
			@Override
			public void run() {
				oppTeam = "Brooklyn Nets";
				oppLabel.setText("Brooklyn Nets");
				gridLayout3.setHorizontalSpacing(3);
				oppLogo.addStyle(SGR.BOLD);
				oppLogo.setText(drawing.getNets());
			}
		});

		// button do wyboru toronto
		Button oppTorontoButton = new Button("---Toronto Raptors---", new Runnable() {
			@Override
			public void run() {
				oppTeam = "Toronto Raptors";
				oppLabel.setText("Toronto Raptors");
				gridLayout3.setHorizontalSpacing(3);
				oppLogo.addStyle(SGR.BOLD);
				oppLogo.setText(drawing.getRaptors());
			}
		});

		// button do wyboru boston
		Button oppBostonButton = new Button("----Boston Celtics---", new Runnable() {
			@Override
			public void run() {
				oppTeam = "Boston Celtics";
				oppLabel.setText("Boston Celtics");
				gridLayout3.setHorizontalSpacing(3);
				oppLogo.addStyle(SGR.BOLD);
				oppLogo.setText(drawing.getCeltics());
			}
		});

		// button do wyboru newYork
		Button oppNewYorkButton = new Button("---New York Knicks---", new Runnable() {
			@Override
			public void run() {
				oppTeam = "New York Knicks";
				oppLabel.setText("New York Knicks");
				gridLayout3.setHorizontalSpacing(3);
				oppLogo.addStyle(SGR.BOLD);
				oppLogo.setText(drawing.getKnicks());
			}
		});

		// button do wyboru philadelphia
		Button oppPhiladelphiaButton = new Button("--Philadelphia 76ers-", new Runnable() {
			@Override
			public void run() {
				oppTeam = "Philadelphia 76ers";
				oppLabel.setText("Philadelphia 76ers");
				gridLayout3.setHorizontalSpacing(3);
				oppLogo.addStyle(SGR.BOLD);
				oppLogo.setText(drawing.get76ers());
			}
		});

		// --my--
		Button brooklynButton = new Button("----Brooklyn Nets----", new Runnable() {
			@Override
			public void run() {
				myTeam = "Brooklyn Nets";
				myLabel.setText("Brooklyn Nets");
				gridLayout3.setHorizontalSpacing(3);
				myLogo.addStyle(SGR.BOLD);
				myLogo.setText(drawing.getNets());
			}
		});

		// button do wyboru toronto
		Button torontoButton = new Button("---Toronto Raptors---", new Runnable() {
			@Override
			public void run() {
				myTeam = "Toronto Raptors";
				myLabel.setText("Toronto Raptors");
				gridLayout3.setHorizontalSpacing(3);
				myLogo.setText(drawing.getRaptors());
				myLogo.addStyle(SGR.BOLD);
			}
		});

		// button do wyboru boston
		Button bostonButton = new Button("----Boston Celtics---", new Runnable() {
			@Override
			public void run() {
				myTeam = "Boston Celtics";
				myLabel.setText("Boston Celtics");
				gridLayout3.setHorizontalSpacing(3);
				myLogo.addStyle(SGR.BOLD);
				myLogo.setText(drawing.getCeltics());
			}
		});

		// button do wyboru newYork
		Button newYorkButton = new Button("---New York Knicks---", new Runnable() {
			@Override
			public void run() {
				myTeam = "New York Knicks";
				myLabel.setText("New York Knicks");
				gridLayout3.setHorizontalSpacing(3);
				myLogo.addStyle(SGR.BOLD);
				myLogo.setText(drawing.getKnicks());
			}
		});

		// button do wyboru philadelphia
		Button philadelphiaButton = new Button("--Philadelphia 76ers-", new Runnable() {
			@Override
			public void run() {
				myTeam = "Philadelphia 76ers";
				myLabel.setText("Philadelphia 76ers");
				gridLayout3.setHorizontalSpacing(3);
				myLogo.addStyle(SGR.BOLD);
				myLogo.setText(drawing.get76ers());
			}
		});
		myPanel.addComponent(brooklynButton);
		myPanel.addComponent(bostonButton);
		myPanel.addComponent(philadelphiaButton);
		myPanel.addComponent(torontoButton);
		myPanel.addComponent(newYorkButton);
		myPanel.addComponent(myInfoLabel);
		myPanel.addComponent(myLabel);

		myPanel.addComponent(brooklynButton.withBorder(Borders.singleLine()));
		myPanel.addComponent(bostonButton.withBorder(Borders.singleLine()));
		myPanel.addComponent(philadelphiaButton.withBorder(Borders.singleLine()));
		myPanel.addComponent(torontoButton.withBorder(Borders.singleLine()));
		myPanel.addComponent(newYorkButton.withBorder(Borders.singleLine()));

		oppPanel.addComponent(oppBrooklynButton);
		oppPanel.addComponent(oppBostonButton);
		oppPanel.addComponent(oppPhiladelphiaButton);
		oppPanel.addComponent(oppTorontoButton);
		oppPanel.addComponent(oppNewYorkButton);
		oppPanel.addComponent(oppInfoLabel);
		oppPanel.addComponent(oppLabel);

		oppPanel.addComponent(oppBrooklynButton.withBorder(Borders.singleLine()));
		oppPanel.addComponent(oppBostonButton.withBorder(Borders.singleLine()));
		oppPanel.addComponent(oppPhiladelphiaButton.withBorder(Borders.singleLine()));
		oppPanel.addComponent(oppTorontoButton.withBorder(Borders.singleLine()));
		oppPanel.addComponent(oppNewYorkButton.withBorder(Borders.singleLine()));

		mainPanel.addComponent(myPanel.withBorder(Borders.doubleLine()));
		mainPanel.addComponent(button.withBorder(Borders.singleLine()));
		mainPanel.addComponent(oppPanel.withBorder(Borders.doubleLine()));


		bigPanel.addComponent(mainPanel);
		bigPanel.addComponent(logoPanel);
		
		
		Label historyLabel = new Label("  ");
		Button historyButton = new Button("Historia Rozgrywek", new Runnable() {
			private String str;
			@Override
			public void run() {
				counterHistory++;
				if(!historia.getHistory().isEmpty()) {
					this.str = "";
					for (int i = 0; i < historyList.size();i++) 
				      { 		      
				          this.str += historyList.get(i); 		
				      }  
					historyLabel.setText(str);
				}
				if(counterHistory == 2) {
					counterHistory = 0;
					historyLabel.setText(" ");
				}
			}
		});
		
		bigPanel.addComponent(historyButton.withBorder(Borders.singleLine()));
		bigPanel.addComponent(historyLabel);
		bigPanel.addComponent(exitButton.withBorder(Borders.singleLine()));
		openWindow.setComponent(bigPanel);

		// openWindow.setComponent(button);
		gui.addWindowAndWait(openWindow);

	}
}
