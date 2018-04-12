
public class Position
{
	protected int r;
	protected int c;

	public Position (Position p) {
		this (p.r, p.c);
	}

	public Position (int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public boolean equals (Position pos)
	{
		if ((r == pos.r) && (c == pos.c))
			return true;
		else
			return false;
	}
	
	public Position offset (Position pos) {
		return new Position (r - pos.r, c - pos.c);
	}
}