
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;

public class LogWindow {
	
	private MultiWindowTextGUI gui;
	
	public LogWindow(MultiWindowTextGUI _gui) {
		this.gui = _gui;
		
		BasicWindow window = new BasicWindow("Menu");
		History historia = new History();
		
		BasicWindow openWindow = new BasicWindow("Lets start");
		Panel panel = new Panel(new GridLayout(4));
		Label label = new Label("                      Podaj nick:");
		Label label2 = new Label("                             ");
		panel.addComponent(label);
		GridLayout gridLayout1 = (GridLayout)panel.getLayoutManager();
		gridLayout1.setHorizontalSpacing(3);
		Panel inPanel = new Panel();
		inPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		
		TextBox textBoxLogin = new TextBox();
		Drawings drawing = new Drawings();
		Label draw = drawing.getBasket();
		 
		Button button = new Button("Play", new Runnable() {
		    @Override 
		    public void run() {
		    	window.close();
		    	new Menu(gui,historia);
		    }
		});
		Button exitButton = new Button("Exit", new Runnable() {
		    @Override 
		    public void run() {
		    	window.close();
		    }
		});
		
		inPanel.addComponent(draw);
		panel.addComponent(textBoxLogin);
		panel.addComponent(button);
		panel.addComponent(label2);
		inPanel.addComponent(panel);
		inPanel.addComponent(exitButton.withBorder(Borders.singleLine()));

		window.setComponent(inPanel);
		
		gui.addListener(null);
		gui.addWindowAndWait(window);
	}
	

}
