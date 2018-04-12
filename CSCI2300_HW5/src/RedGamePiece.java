import java.awt.*;
import java.awt.geom.*;

public class RedGamePiece extends GamePiece
{
	public RedGamePiece (int row, int col, GameSquare[][] squares) {
		super (row, col, Color.RED , squares);
	}

	public RedGamePiece (Position pos, GameSquare[][] squares) {
		this (pos.r, pos.c, squares);
	}
	
	protected boolean validNonJump (Position targetPos)
	{
		Position dp = targetPos.offset (pos);
		int dr = dp.r;
		int dc = dp.c;

		if (isKing()){
			if ((dr == 1 || dr == -1) && ((dc == -1) || (dc == 1)))
			{
				if (squares[pos.r + dr][pos.c + dc].getPiece() == null)
					return true;
			}
		}
		
		if ((dr == -1) && ((dc == -1) || (dc == 1)))
		{
			if (squares[pos.r + dr][pos.c + dc].getPiece() == null)
				return true;
		}
		
		return false;
	}
	
	protected boolean validJump (Position targetPos)
	{
		Position dp = targetPos.offset(pos);
		int dr = dp.r;
		int dc = dp.c;
		
		if (this.isKing()) {
			if ((dr == 2 || dr == -2) && ((dc == -2) || (dc == 2))) {
				if (squares[pos.r + dr][pos.c + dc].getPiece() == null) {
					if (squares[pos.r + dr/2][pos.c + dc/2].getPiece().getColor() == Color.BLACK)
						return true;
				}
			}			
		}
		
		if ((dr == -2) && ((dc == -2) || (dc == 2))) {
			if (squares[pos.r + dr][pos.c + dc].getPiece() == null) {
				if (squares[pos.r + dr/2][pos.c + dc/2].getPiece().getColor() == Color.BLACK)
					return true;
			}
		}
		
		return false;
	}
	
	public void becomeKing() {
		if (pos.r == 0)
			king = true;
	}
	
	public void drawPiece (Graphics2D g2, int x, int y, int width, int height)
	{
		Ellipse2D.Double outline = new Ellipse2D.Double (x + width * DIST_FROM_EDGE + LINE_WIDTH / 2,
														 y + height * DIST_FROM_EDGE + LINE_WIDTH / 2,
														 width * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH,
														 height * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH);

	
		if (isKing()) {
			g2.setColor(new Color(255,117,117)); //Pink means king
		}
		else {
			g2.setColor (Color.RED);
		}
			
		BasicStroke stroke = new BasicStroke (LINE_WIDTH);
		g2.setStroke (stroke);
		g2.fill (outline);

		if (isKing()) {
			Point2D.Double k1 = new Point2D.Double(30, 20);
			Point2D.Double k2 = new Point2D.Double(30, 50);
			Point2D.Double k3 = new Point2D.Double(45, 20);
			Point2D.Double k4 = new Point2D.Double(30, 35);
			Point2D.Double k5 = new Point2D.Double(45, 50);
			Line2D.Double line = new Line2D.Double(k1, k2);
			Line2D.Double line1 = new Line2D.Double(k3, k4);
			Line2D.Double line2 = new Line2D.Double(k5, k4);
			
			g2.setColor(Color.BLACK);
			g2.draw(line);
			g2.draw(line1);
			g2.draw(line2);
		}
 		
		
		if (selected)
		{
			g2.setColor (Color.GREEN);
			g2.draw (outline);
		}
	}


}