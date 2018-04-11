import java.awt.*;

public abstract class GamePiece implements PlayableGamePiece
{
	protected final static double DIST_FROM_EDGE = 0.1;
	protected final static int LINE_WIDTH = 5;

	protected boolean selected = false;
	protected Position pos = null;
	protected Player player = Player.EMPTY;
	protected boolean king = false;
	
	protected GameSquare[][] squares;

	public GamePiece (int row, int col, Player p, GameSquare[][] squares) {
		this.squares = squares;
		pos = new Position (row, col);
		player = p;
	}

	public GamePiece (Position pos, Player p, GameSquare[][] squares) {
		this (pos.r, pos.c, p, squares);
	}

	public Position pos () {
		return pos;
	}

	public Player getPlayer () {
		return player;
	}
	
	public boolean isKing () {
		return king;
	}
	
	public void setPieceSelected (boolean b)
	{
		if ((b == true) && (player != Player.EMPTY))
			selected = true;
		else
			selected = false;
	}

	public boolean isPieceSelected() {
		return selected;
	}

	public boolean validMove (Position targetPos)
	{
		if (validNonJump (targetPos))
			return true;
		else if (validJump (targetPos))
			return true;
				
		return false;
	}
	
	protected abstract boolean validNonJump (Position targetPos);	
	
	protected abstract boolean validJump (Position targetPos);
	
	protected abstract void becomeKing();
	
	public void move (Position targetPos)
	{
		if (validNonJump (targetPos))
		{
			squares[pos.r][pos.c].removePiece ();
			pos = targetPos;
			squares[targetPos.r][targetPos.c].setPiece (this);
		}
		else if (validJump (targetPos))
		{
			squares[pos.r][pos.c].removePiece ();
			int delOpRow = (pos.r + targetPos.r)/2;
			int delOpCol = (pos.c + targetPos.c)/2;
			if (squares[delOpRow][delOpCol].getPiece().getPlayer() == Player.BLACK) {
			}
			if (squares[delOpRow][delOpCol].getPiece().getPlayer() == Player.RED) {
			}
			squares[delOpRow][delOpCol].removePiece();
			pos = targetPos;
			squares[targetPos.r][targetPos.c].setPiece (this);
		}
		
		becomeKing();
	}
	
	public abstract void drawPiece (Graphics2D g2, int x, int y, int width, int height);
}
