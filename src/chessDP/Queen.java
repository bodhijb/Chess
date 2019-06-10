package chessDP;

import java.io.Serializable;

public class Queen implements Piece, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5135373011948688065L;
	private String name;
	private Colour colour;
	private int x;
	private int y;
	Game activeGame;

	public Queen(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		activeGame = new ChessGame();
	}

	public Queen() {
		
	}

	@Override
	public boolean isMoveLegal(Piece[][] board, int x, int y) {
		boolean legalMove = false;
		int cX = this.getX();
		int cY = this.getY();
		int xDiff = x - cX;
		int yDiff = y - cY;

		if ((Math.abs(xDiff) == Math.abs(yDiff)) || (xDiff == 0 && yDiff != 0) || (xDiff != 0 && yDiff == 0)) {
			legalMove = true;
		}
		
		if (legalMove) {
			if(isPathClear(board, x, y)) {
				legalMove = true;
			}
			else {
				legalMove = false;
			}
		}
		if (!legalMove) {
			System.out.format("Pawn %d:%d Illegal move %n", cX, cY);
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

		int value = (xDiff < 0 && yDiff > 0 ? 1
				: xDiff == 0 && yDiff > 0 ? 2
						: xDiff > 0 && yDiff > 0 ? 3
								: xDiff > 0 && yDiff == 0 ? 4
										: xDiff > 0 && yDiff < 0 ? 5
												: xDiff == 0 && yDiff < 0 ? 6 : xDiff < 0 && yDiff < 0 ? 7 : 8);
		switch (value) {
		case 1:
			for (int i = cX - 1, j = cY + 1; i > x && j < y; i--, j++) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 2:
			for (int i = cX, j = cY + 1; i == x && j < y; j++) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 3:
			for (int i = cX + 1, j = cY + 1; i < x && j < y; i++, j++) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 4:
			for (int i = cX + 1, j = cY; i < x && j == y; i++) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 5:
			for (int i = cX + 1, j = cY - 1; i < x && j > y; i++, j--) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 6:
			for (int i = cX, j = cY - 1; i == x && j > y; j--) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 7:
			for (int i = cX - 1, j = cY - 1; i > x && j > y; i--, j--) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		case 8:
			for (int i = cX - 1, j = cY; i > x && j == y; i--) {
				Piece temp = board[i][j];
				if (temp.getColour() != Colour.GREY) {
					pathClear = false;
					break;
				}
			}
			break;
		default:
			pathClear = false;
			break;
		}

		if (pathClear) {
			return pathClear;
		} else {
			System.out.format("Queen IPC. Path not clear. %n");
			return false;
		}

	}

	@Override
	public boolean makeTheMove(Piece[][] board, int x, int y) {
		boolean moved = false;
		int cX = this.getX();
		int cY = this.getY();
		Piece temp = board[x][y];
		Queen queen = this;
		board[x][y] = queen;
		queen.setX(x);
		queen.setY(y);
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
			System.out.println("CheckMate target is not opposing colour");
			return false;
		}

		possible = isMoveLegal(board, x, y);

		System.out.format("Queen %d:%d. Possible to check? %b %n", cX, cY, possible);
		return possible;

	}

	@Override
	public void infoAboutMove(int prevX, int prevY, int newX, int newY) {

		System.out.format("%s %s moved from %d:%d to %d:%d. ", this.getColour().toString(), this.toString(), prevX,
				prevY, newX, newY);

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
		return "Queen".toUpperCase();
	}

}
