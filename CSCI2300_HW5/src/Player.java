import java.awt.Color;

public class Player {
	
	private Color id = null;
	private int counter = 0;
	private boolean active = false;
	
	public Player(int num) {
		if (num == 1)
			id = Color.RED;
		else if (num == 2)
			id = Color.BLACK;
		else
			id = null;
	}
	
	public Player(Player p) {			// Copy Constructor
		id = p.getID();
		counter = p.getCount();
		active = p.isActive();
	}
	
	public void incCount() {
		counter++;
	}
	
	public void decCount() {
		counter--;
	}
	
	public void resetCount() {
		counter = 0;
	}
	
	public int getCount() {
		return counter;
	}
	
	public void setID(Color col) {
		id = col;
	}
	
	public Color getID() {
		return id;
	}
	
	public void toggleActive() {
		if (active == true)
			active = false;
		else
			active = true;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public Player switchPlayer(Player other) {
		if (this.isActive()) {
			this.toggleActive();
			other.toggleActive();
			return other;
		}	
//		else if (p2.isActive()) {
//			p1.toggleActive();
//			p2.toggleActive();
//			return p1;
//		}
		else
			return new Player(0);
	}
}


/*
public enum Player {
	EMPTY, RED, BLACK;
	
	public static Player switchPlayer (Player p)
	{
		if (p == Player.RED)
			return Player.BLACK;
		else if (p == Player.BLACK)
			return Player.RED;
		else
			return Player.EMPTY;
	}
	
	public static Player getOpponent (Player p) {
		return switchPlayer (p);
	}
}
*/