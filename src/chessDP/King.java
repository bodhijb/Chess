package chessDP;

import java.io.Serializable;

public class King implements Piece, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1829977338155092536L;
	private String name;
	private Colour colour;
	private int x;
	private int y;
	Game activeGame;

	public King(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		activeGame = new ChessGame();
	}

	public King() {
		
	}

	@Override
	public boolean isMoveLegal(Piece[][] board, int x, int y) {
		boolean legalMove = false;
		int cX = this.getX();
		int cY = this.getY();
		int xDiff = x - cX;
		int yDiff = y - cY;

		if ((xDiff > -2 && xDiff < 2) && (yDiff > -2 && yDiff < 2)) {
			if (isPathClear(board, x, y)) {
				legalMove = true;
			}
		}
		if (legalMove) {
			return legalMove;
		} else {
			System.out.println("Illegal move");
			return false;
		}

	}

	@Override
	public boolean isPathClear(Piece[][] board, int x, int y) {
		boolean pathClear = true;
		Piece newPiece = board[x][y];

		if (newPiece.getColour() == this.getColour()) {
			return false;
		} 
		if (pathClear) {
			return pathClear;
		} else {
			System.out.format("King IPC. Path not clear. %n");
			return false;
		}

	}

	@Override
	public boolean makeTheMove(Piece[][] board, int x, int y) {
		boolean moved = false;
		int curX = this.getX();
		int curY = this.getY();
		Piece temp = board[x][y];
		King king = this;
		board[x][y] = king;
		king.setX(x);
		king.setY(y);
		board[curX][curY] = temp;
		temp.setX(curX);
		temp.setY(curY);
		if (temp.getColour() == Colour.GREY) {
			board[curX][curY] = temp;
			temp.setX(curX);
			temp.setY(curY);
		} else {
			board[curX][curY] = new Spare("Spare", Colour.GREY);
			board[curX][curY].setX(curX);
			board[curX][curY].setY(curY);
		}

		temp.setX(curX);
		temp.setY(curY);
		moved = true;
		infoAboutMove(curX, curY, x, y);
		return moved;
	}
	
	@Override
		public void infoAboutMove(int prevX, int prevY, int newX, int newY) {
			System.out.print(this.getColour().toString() + " " + this.toString() + " moved from " + prevX + ":" + prevY 
					+ " to " + newX + ":" + newY + ". ");
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Colour getColour() {
		return colour;
	}

	@Override
	public void setColour(Colour colour) {
		this.colour = colour;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
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

	@Override
	public Game getActiveGame() {
		return activeGame;
	}

	@Override
	public void setActiveGame(Game activeGame) {
		this.activeGame = activeGame;
	}

	@Override
	public String toString() {
		return "King".toUpperCase();
	}

	@Override
	public boolean canICheck(Piece[][] board, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}





}
