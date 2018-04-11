
public class Player {

	protected String id;
	
	public Player(String color) {
		id = color;		
	}
	
	public String getID() {
		return id;
	}
	
	public static Player switchPlay(Player p) {
		
		if (p.getID() == "RED")
			return new Player("BLACK");
		if (p.getID() == "BLACK")
			return new Player("RED");
		else
			return new Player("");
	}
	
	
/*	public static Player switchPlayer (Player p)
	{
		if (p == Player.RED)
			return Player.BLACK;
		else if (p == Player.BLACK)
			return Player.RED;
		else
			return Player.EMPTY;
	}*/
	
	/*public static Player getOpponent (Player p) {
		return switchPlayer (p);
	}*/
}
