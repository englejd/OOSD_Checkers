import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
   A component that shows a scene composed of shapes.
 */
@SuppressWarnings("serial")
public class CheckerBoard extends JPanel
{
	protected final static int SQUARES_1D = 8;

	private final GameSquare[][] squares;

	private Player player;
	private GamePiece selectedPiece = null;
	public int redCounter;
	public int blackCounter;
	
	public CheckerBoard ()
	{
		super ();

		setLayout (new GridLayout (8,8,0,0));
		
		squares = new GameSquare[SQUARES_1D][SQUARES_1D];

		int numPieceSpots = 0;

		for (int r = 0; r < SQUARES_1D; r++)
			for (int c = 0; c < SQUARES_1D; c++)
			{
				if ((r + c) % 2 == 1)
					squares[r][c] = new GameSquare (r, c, new Color(202, 144, 79)); //"White" squares
				else
				{
					squares[r][c] = new GameSquare (r, c, new Color(121, 93, 62)); //"Black"/valid squares
					
					if (numPieceSpots <= 11) {
						squares[r][c].setPiece (new BlackGamePiece(r, c, squares));
						blackCounter++;
					}
					
					if (numPieceSpots >= 20) {
						squares[r][c].setPiece (new RedGamePiece (r, c, squares));
						redCounter++;
					}

					numPieceSpots++;

					squares[r][c].addMouseListener (new GameSquareMouseListener (squares[r][c]));
				}

				add (squares[r][c]);
			}
		
		player = Player.RED;  //ME
	}

	public class GameSquareMouseListener extends MouseAdapter
	{
		GameSquare sq;

		public GameSquareMouseListener (GameSquare sq) {
			this.sq = sq;
		}
		
		public void mousePressed (MouseEvent event)
		{
			Point mousePoint = event.getPoint();

			if (sq.contains (mousePoint))
			{
				if (selectedPiece == null)
				{
					if (sq.getPiece() != null)
					{
						selectedPiece = sq.getPiece();
						sq.getPiece().setPieceSelected (true);
					}
				}
				else
				{
					Position pos = sq.getPosition();

					if (pos.equals (selectedPiece.pos()))
					{
						selectedPiece.setPieceSelected (false);	 // de-select piece
						selectedPiece = null;					 //  "          "
					}
					else if (selectedPiece.validMove (pos))
					{
						if (selectedPiece.getPlayer() == player) {	//Checks if the selected piece is the same as the active player
							selectedPiece.move (pos);				 // move selected piece
							selectedPiece.setPieceSelected (false);	 // de-select piece
							selectedPiece = null;					 //  "          "
							player = player.switchPlayer(player);
						}
					}
				}
			}
			repaint();
		}
	}
}
