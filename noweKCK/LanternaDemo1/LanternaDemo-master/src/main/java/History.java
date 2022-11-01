import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class History {
	
	private List<String> history = new ArrayList<>();
	
	
	public History() {
		
	}
	public List<String> getHistory(){
		return this.history;
	}
	public void addToHistory(String myTeam, String oppTeam, int myScore, int oppScore) {
		String str = myTeam+" vs "+oppTeam+"\n-------------"+Integer.toString(myScore)+":"+Integer.toString(oppScore)+"--------------\n\n";
		history.add(str);
	}

}
