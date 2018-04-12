import java.awt.*;
import java.awt.geom.*;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameSquare extends JComponent
{
	private Position pos = null;
	private Color squareColor;

	private GamePiece piece = null;

	public GameSquare (int row, int col, Color squareColor)
	{
		super ();

		pos = new Position (row, col);
		this.squareColor = squareColor;
	}

	public GameSquare (Position pos, Color squareColor) {
		this (pos.r, pos.c, squareColor);
	}

	public Position getPosition () {
		return pos;
	}

	public void setPiece (GamePiece p) {
		piece = p;
	}

	public void removePiece () {
		setPiece (null);
	}

	public GamePiece getPiece () {
		return piece;
	}

	public void paintComponent (Graphics g)
	{
		super.paintComponents (g);

		Graphics2D g2 = (Graphics2D) g;

		int height = getHeight();
		int width = getWidth();
		
		Rectangle2D.Double bkgnd = new Rectangle2D.Double (0, 0, width, height);
		g2.setColor (squareColor);
		g2.fill (bkgnd);

		if (piece != null)
			piece.drawPiece (g2, 0, 0, width, height);
	}   
}