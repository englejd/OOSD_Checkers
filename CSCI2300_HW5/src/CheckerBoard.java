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

	private Player player1;
	private Player player2;
	private Player active;
	private Player inactive;
	private GamePiece selectedPiece = null;
	
	public CheckerBoard (Player p1, Player p2)
	{
		super ();

		setLayout (new GridLayout (8,8,0,0));
		
		squares = new GameSquare[SQUARES_1D][SQUARES_1D];

		int numPieceSpots = 0;
		player1 = p1;  // Red Player
		player2 = p2;  // Black Player

		for (int r = 0; r < SQUARES_1D; r++)
			for (int c = 0; c < SQUARES_1D; c++)
			{
				Color lightBrown = new Color(202, 144, 79);
				Color darkBrown = new Color(121, 93, 62);
				
				if ((r + c) % 2 == 1)
					squares[r][c] = new GameSquare (r, c, lightBrown); //"White" squares
				else
				{
					squares[r][c] = new GameSquare (r, c, darkBrown); //"Black"/valid squares
					
					if (numPieceSpots <= 11) {
						squares[r][c].setPiece (new BlackGamePiece(r, c, squares));
						player2.incCount();
					}
					
					if (numPieceSpots >= 20) {
						squares[r][c].setPiece (new RedGamePiece (r, c, squares));
						player1.incCount();
					}

					numPieceSpots++;

					squares[r][c].addMouseListener (new GameSquareMouseListener (squares[r][c]));
				}

				add (squares[r][c]);
			}
		
		player1.toggleActive();		// sets Player1 as active
		
		active = new Player(player1);
		inactive = new Player(player2);
	}

	public void reset() {
//		setLayout (new GridLayout (8,8,0,0));
		
//		squares = new GameSquare[SQUARES_1D][SQUARES_1D];

		int numPieceSpots = 0;
		player1.resetCount();;  // Red Player
		player2.resetCount();  // Black Player

		for (int r = 0; r < SQUARES_1D; r++)
			for (int c = 0; c < SQUARES_1D; c++)
			{
				
				squares[r][c].removePiece();
				
				if((r + c) % 2 != 1) {
					if (numPieceSpots <= 11) {
						squares[r][c].setPiece (new BlackGamePiece(r, c, squares));
						player2.incCount();
					}
					
					if (numPieceSpots >= 20) {
						squares[r][c].setPiece (new RedGamePiece (r, c, squares));
						player1.incCount();
					}
	
					numPieceSpots++;
					//squares[r][c].addMouseListener (new GameSquareMouseListener (squares[r][c]));
				}
				
				add(squares[r][c]);
			}
		
		player1.toggleActive();		// sets Player1 as active
		
		active = player1;
		inactive = player2;
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
						if (selectedPiece.getColor() == active.getID()) {	//Checks if the selected piece is the same as the active player
							selectedPiece.move (pos, inactive);				 // move selected piece
							selectedPiece.setPieceSelected (false);	 // de-select piece
							selectedPiece = null;					 //  "          "
							//player = player.switchPlayer(player);

							if (player1.isActive()) {
								player1.switchPlayer(player2);
								active = player2;
								inactive = player1;
							}
							else if (player2.isActive()) {
								player2.switchPlayer(player1); 
								active = player1;
								inactive = player2;
							}
						
							System.out.println("-------------------------");
							System.out.println("Player 1 piece count: " + player1.getCount());
							System.out.println("Player 2 piece count: " + player2.getCount());
							System.out.println("-------------------------");
						}
					}
				}
			}
			repaint();
		}
	}
}