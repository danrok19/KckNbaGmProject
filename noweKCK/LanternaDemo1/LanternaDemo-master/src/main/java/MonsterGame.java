import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;
import javax.swing.Action;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
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
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class MonsterGame {

	public static void main(String[] args) throws Exception {
		
		 DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
		 terminalFactory.setInitialTerminalSize(new TerminalSize(120,60));
		 Terminal terminal = terminalFactory.createTerminal();
		 	 
		 Screen screen = new TerminalScreen(terminal);
		 TextGraphics tg = screen.newTextGraphics();
		 screen.startScreen();
		 
		 tg.setForegroundColor(new TextColor.RGB(0,255,0));

		tg.putString(0, 0,"            BB                      ");
        tg.putString(0, 1,"            BB                      ");
        tg.putString(0, 2,"            BB                      ");
        tg.putString(0, 3,"NN,NNNNNN,  BB,dPPYba,  ,adAAAAba,");
        tg.putString(0, 4,"NNN'   `\"N BBB'    \"8a        `AA");
        tg.putString(0, 5,"NN       NN BB       dBB ,adAAAAAAA");
        tg.putString(0, 6,"NN       NN BBB,   ,BB\" 88,    ,AA");
        tg.putString(0, 7,"NN       NN BB\"BBBBB\"'  `\"AAAAP\"AA");
        
        tg.putString(42, 0, "                        __-------_");
        tg.putString(42, 1,"                       -   -ooooooo");
        tg.putString(42, 2,"                      |   |         o");
        tg.putString(42, 3,"                     |   |           o");
        tg.putString(42, 4,"                     |   |           o");
        tg.putString(42, 5,"                     |   /           o");
        tg.putString(42, 6,"                     |  | o         o");
        tg.putString(42, 7,"                     |  |   ooooooo");
        tg.putString(42, 8,"                     |   |");
        tg.putString(42, 9,"                     |   |");
        tg.putString(42, 10,"                     |   |               __________________________");
        tg.putString(42, 11,"                    |    |              |__________________________|");
        tg.putString(42, 12,"                    |    |               \\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/");
        tg.putString(42, 13,"                    |    |                \\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/");
        tg.putString(42, 14,"             __-----|    |                 \\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/");
        tg.putString(42, 15,"            /      /      |                 \\/\\/\\/\\/\\/\\/\\/\\/\\/\\/");
        tg.putString(42, 16,"            |     |       |                  |/\\/\\/\\/\\/\\/\\/\\/\\|");
        tg.putString(42, 17,"            |   _/        |                   |/\\/\\/\\/\\/\\/\\/\\|");
        tg.putString(42, 18,"            | _/           \\                  |\\/\\/\\/\\/\\/\\/\\/|");
        tg.putString(42, 19,"            _/              \\                 |/\\/\\/\\/\\/\\/\\\\/|");
        tg.putString(42, 20,"        |                    |");
        tg.putString(42, 21,"       /|                    /");
        tg.putString(42, 22,"      |  \\                  |");
        tg.putString(42, 23,"      |   \\                /");
        tg.putString(42, 24,"     /    /\\              |");
        tg.putString(42, 25,"    |    |  \\             |");
        tg.putString(42, 26,"    |    |   |            |");
        tg.putString(42, 27,"    |   |     \\          |");
        tg.putString(42, 28,"    |   |      |         |");
        tg.putString(42, 29,"    \\   |       |________|");
        tg.putString(42, 30,"     |  |      |_________|");
        tg.putString(42, 31,"     |  |     |           |");
        tg.putString(42, 32," ____|  |    |             |");
        tg.putString(42, 33," \\_____/     |             |");
        tg.putString(42, 34,"            |              |");
        tg.putString(42, 35,"            |    |         |");
        tg.putString(42, 36,"            |    |        |");
        tg.putString(42, 37,"             |   |        |");
        tg.putString(42, 38,"              |_|_______|");
        tg.putString(42, 39,"               | /     |");
        tg.putString(42, 40,"               ||      |");
        tg.putString(42, 41,"               ||      |");
        tg.putString(42, 42,"               | |    |");
        tg.putString(42, 43,"               ||      |");
        tg.putString(42, 44,"               ||     |");
        tg.putString(42, 45,"               ||     |");
        tg.putString(42, 46,"               |     |");
        tg.putString(42, 47,"               |    |");
        tg.putString(42, 48,"               ||  |");
        tg.putString(42, 49,"               ||__|");
        tg.putString(42, 50,"              | /  |");
        tg.putString(42, 51,"              ||___||");
        tg.putString(42, 52,"              |\\   \\ |");
        tg.putString(42, 53,"              | \\   \\|");
        tg.putString(42, 54,"               \\ \\__\\");
        tg.putString(42, 55,"                |__/");
		screen.refresh();
		screen.readInput(); 
		MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
				new EmptySpace(TextColor.ANSI.BLUE));
		

		Thread.sleep(1000);
		new LogWindow(gui);
		terminal.bell();
		screen.close();
		
    }

}
