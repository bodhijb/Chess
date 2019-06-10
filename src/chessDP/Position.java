package chessDP;

import java.util.ArrayList;
import java.util.List;

public class Position {
	int x;
	int y;
	private boolean free;
	private static List<Position> positions = new ArrayList<>();
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	
	public Position addPosition(int x, int y) {
		Position a = new Position(x, y);
		if(!positions.contains(a))
			positions.add(a);
		return positions.get(positions.indexOf(a));
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	public boolean equals(Position pos) {
		if (this == pos)
			return true;
		if (pos == null)
			return false;
		if (getClass() != pos.getClass())
			return false;
		Position other = pos;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public static List<Position> getPositions() {
		return positions;
	}

	public static void setPositions(List<Position> positions) {
		Position.positions = positions;
	}



	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	
	
	
	
	
}
