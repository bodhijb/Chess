package chessDP;

import java.io.Serializable;

public class Rook implements Piece, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 523457698910694460L;
	private String name;
	private Colour colour;
	private int x;
	private int y;
	private Game activeGame;

	public Rook(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		activeGame = new ChessGame();
	}

	public Rook() {
		
	}

	@Override
	public boolean isMoveLegal(Piece[][] board, int x, int y) {
		boolean legalMove = false;
		int cX = this.getX();
		int cY = this.getY();
		int xDiff = x - cX;
		int yDiff = y - cY;

		if ((xDiff == 0 && yDiff != 0) || (xDiff != 0 && yDiff == 0)) {
			legalMove = true;
		}
		if (legalMove) {
			if (isPathClear(board, x, y)) {
				legalMove = true;
			} else {
				legalMove = false;
			}
		}
		if (!legalMove) {
			System.out.format("Rook %d:%d Illegal move %n", cX, cY );
		}
		return legalMove;

	}

	@Override
	public boolean isPathClear(Piece[][] board, int x, int y) {
		boolean pathClear = true;
		int cX = this.getX();
		int cY = this.getY();
		int xDiff = x - cX;
		int yDiff = y - cY;
		Piece newPiece = board[x][y];
		if (newPiece.getColour() == this.getColour()) {
			return false;
		}

		int value = (xDiff == 0 && yDiff > 0 ? 1
				: xDiff > 0 && yDiff == 0 ? 2 : xDiff == 0 && yDiff < 0 ? 3 : xDiff < 0 && yDiff == 0 ? 4 : 0);
		switch (value) {
		case 1:
			for (int i = cX, j = cY + 1; i == x && j < y; j++) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 2:
			for (int i = cX + 1, j = cY; i < x && j == y; i++) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 3:
			for (int i = cX, j = cY - 1; i == x && j > y; j--) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 4:
			for (int i = cX - 1, j = cY; i > x && j == y; i--) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		}

		if (pathClear) {
			return pathClear;
		} else {
			System.out.format("Rook IPC. Path not clear. %n");
			return false;
		}

	}

	@Override
	public boolean makeTheMove(Piece[][] board, int x, int y) {
		boolean moved = false;
		int curX = this.getX();
		int curY = this.getY();
		Piece temp = board[x][y];
		Rook rook = this;
		board[x][y] = rook;
		rook.setX(x);
		rook.setY(y);
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
	public boolean canICheck(Piece[][] board, int x, int y) {
		boolean possible = false;
		int cX = this.getX();
		int cY = this.getY();		
		Piece target = board[x][y];
		
		if(target.getClass() != King.class) {
			System.out.println("Checkmate target is not a King");
			return false;
		}
		else if((this.getColour() == Colour.WHITE && target.getColour() != Colour.BLACK) ||
		(this.getColour() == Colour.BLACK && target.getColour() != Colour.WHITE)){
			System.out.println("CheckMate target is not opposing colour");
			return false;
		}

		possible = isMoveLegal(board, cX, cY);


		System.out.format("Rook %d:%d. Possible to check? %b %n", cX, cY, possible);
		return possible;

	}

	@Override
	public void infoAboutMove(int prevX, int prevY, int newX, int newY) {

		System.out.format("%s %s moved from %d:%d to %d:%d. ", this.getColour().toString(), this.toString(), prevX,
				prevY, newX, newY);

	}

	public void moveCastle() {

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
		return "Rook".toUpperCase();
	}

}
