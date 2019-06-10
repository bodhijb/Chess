package chessDP;

import java.io.Serializable;

public class Knight implements Piece, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7140478159600989935L;
	private String name;
	private Colour colour;
	private int x;
	private int y;
	Game activeGame;

	public Knight(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		activeGame = new ChessGame();
	}

	public Knight() {
		
	}

	@Override
	public boolean isMoveLegal(Piece[][] board, int x, int y) {
		boolean legalMove = false;
		int curX = this.getX();
		int curY = this.getY();
		int xDiff = x - curX;
		int yDiff = y - curY;

		int value = ((xDiff == -2 && yDiff == 1) ? 1
				: (xDiff == 1 && yDiff == 2) ? 2
						: (xDiff == 2 && yDiff == -1) ? 3
								: (xDiff == -1 && yDiff == -2) ? 4
										: (xDiff == -1 && yDiff == 2) ? 5
												: (xDiff == 2 && yDiff == 1) ? 6 : (xDiff == 1 && yDiff == -2) ? 7
														: (xDiff == -2 && yDiff == -1) ? 8 : 9);

		switch (value) {
		case 1:
			System.out.format("1 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 2:
			System.out.format("2 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 3:
			System.out.format("3 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 4:
			System.out.format("4 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 5:
			System.out.format("5 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 6:
			System.out.format("6 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 7:
			System.out.format("7 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 8:
			System.out.format("8 worked %d %d %n", xDiff, yDiff);
			legalMove = true;
			break;
		case 9:
			System.out.format("9 didnt work %d %d %n", xDiff, yDiff);
			return false;
		default:
			System.out.format("def didnt work %d %d %n", xDiff, yDiff);
			return false;
		}

		if (legalMove && isPathClear(board, x, y)) {
			return true;
		} else {
			System.out.format("Illegal move %n");
			return false;
		}

	}

	@Override
	public boolean isPathClear(Piece[][] board, int x, int y) {
		boolean pathClear = false;
		Colour objColour = this.getColour();
		Piece otherObj = board[x][y];
		Colour otherColour = otherObj.getColour();
		if (objColour == otherColour) {
			pathClear = false;
		} else {
			pathClear = true;
		}
		if (pathClear) {
			return pathClear;
		} else {
			System.out.format("Knight IPC. Path not clear %n");
			return false;
		}

	}

	@Override
	public boolean makeTheMove(Piece[][] board, int x, int y) {
		boolean moved = false;
		int cX = this.getX();
		int cY = this.getY();
		Piece temp = board[x][y];
		Knight knight = this;
		board[x][y] = knight;
		knight.setX(x);
		knight.setY(y);
		board[cX][cY] = temp;
		temp.setX(cX);
		temp.setY(cY);
		if (temp.getColour() == Colour.GREY) {
			board[cX][cY] = temp;
			temp.setX(cX);
			temp.setY(cY);
		} else {
			board[cX][cY] = new Spare("Spare", Colour.GREY);
			board[cX][cY].setX(cX);
			board[cX][cY].setY(cY);
		}

		temp.setX(cX);
		temp.setY(cY);
		moved = true;
		infoAboutMove(cX, cY, x, y);
		return moved;
	}

	@Override
	public boolean canICheck(Piece[][] board, int x, int y) {
		boolean possible = false;
		int cX = this.getX();
		int cY = this.getY();
		Piece target = board[x][y];
		
		if (target.getClass() != King.class) {
			System.out.println("Checkmate target is not a King");
			return false;
		} else if ((this.getColour() == Colour.WHITE && target.getColour() != Colour.BLACK)
				|| (this.getColour() == Colour.BLACK && target.getColour() != Colour.WHITE)) {
			System.out.format("CheckMate target is not opposite colour");
			return false;
		}

		possible = isMoveLegal(board, x, y);
			
		System.out.format("Knight %d:%d. Possible to check? %b %n" ,cX, cY, possible);

		return possible;

	}

	@Override
	public void infoAboutMove(int prevX, int prevY, int newX, int newY) {
		
		System.out.format("%s %s moved from %d:%d to %d:%d. ", this.getColour().toString(), this.toString(), prevX,
				prevY, newX, newY);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;

	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public Game getActiveGame() {
		return activeGame;
	}

	public void setActiveGame(Game activeGame) {
		this.activeGame = activeGame;
	}

	@Override
	public String toString() {
		return "Knight".toUpperCase();
	}

}
