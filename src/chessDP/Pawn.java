package chessDP;

import java.io.Serializable;

public class Pawn implements Piece, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2811214652924716071L;
	private String name;
	private Colour colour;
	private int x;
	private int y;
	Game activeGame;
	boolean first;

	public Pawn(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		activeGame = new ChessGame();

	}

	public Pawn() {
		
	}

	@Override
	public boolean isMoveLegal(Piece[][] board, int x, int y) {
		boolean legalMove = false;
		int cX = this.getX();
		int cY = this.getY();
		int xDiff = x - cX;
		int yDiff = y - cY;
		System.out.format("diff %d %d %n", xDiff, yDiff);
		System.out.println(this.getColour());
		if (this.getColour() == Colour.WHITE) {
			switch (xDiff) {
			case 1:
				System.out.format("1: worked %d %n", xDiff);
				legalMove = true;
				break;
			default:
				System.out.format("1: didnt work %d %n", xDiff);
				return false;
			}
		} else if (this.getColour() == Colour.BLACK) {
			switch (xDiff) {
			case -1:
				System.out.format("2: worked %d %n", xDiff);
				legalMove = true;
				break;
			default:
				System.out.format("2: didnt work %d %n", xDiff);
				return false;
			}
		}

		switch (yDiff) {
		case -1:
			System.out.format("3: worked %n");
			legalMove = true;
			break;
		case 0:
			System.out.format("3: worked %n");
			legalMove = true;
			break;
		case 1:
			System.out.format("3: worked %n");
			legalMove = true;
			break;
		default:
			System.out.format("3: didnt work %n");
			return false;

		}

		if (legalMove && isPathClear(board, x, y)) {
			System.out.format("4: worked %n");
			legalMove = true;
		}

		if (legalMove) {
			return legalMove;
		} else {
			System.out.format("Pawn %d:%d Illegal move %n", cX, cY);
			return false;
		}
	}

	public boolean isFirstMoveLegal(Piece[][] board, int x, int y) {
		boolean legalMove = false;
		int cX = this.getX();
		int cY = this.getY();
		int xDiff = x - cX;
		int yDiff = y - cY;
		System.out.format("diffs: %d %d  %n", xDiff, yDiff);

		if (this.getColour() == Colour.WHITE) {
			switch (xDiff) {
			case 1:
				System.out.format("1: worked %d %n", xDiff);
				legalMove = true;
				break;
			case 2:
				System.out.format("1: worked %d %n", xDiff);
				legalMove = true;
				break;
			default:
				System.out.format("1: didnt work %d %n", xDiff);
				return false;
			}
		}

		else if (this.getColour() == Colour.BLACK) {
			switch (xDiff) {
			case -1:
				System.out.format("2: worked %d %n", xDiff);
				legalMove = true;
				break;
			case -2:
				System.out.format("2: worked %d %n", xDiff);
				legalMove = true;
				break;
			default:
				System.out.format("2: didnt work %d %n", xDiff);
				return false;
			}
		}

		switch (yDiff) {
		case -1:
			System.out.format("3: worked %n");
			legalMove = true;
			break;
		case 0:
			System.out.format("3: worked %n");
			legalMove = true;
			break;
		case 1:
			System.out.format("3: worked %n");
			legalMove = true;
			break;
		default:
			System.out.format("3: didnt work %n");
			return false;

		}

		if (legalMove && isFirstMovePathClear(board, x, y)) {
			System.out.format("4: worked %n");
			legalMove = true;
			this.setFirst(false);
			return legalMove;
		} else {
			System.out.format("Pawn %d:%d Illegal move %n", cX, cY);
			return false;
		}

	}

	@Override
	public boolean isPathClear(Piece[][] board, int x, int y) {
		Piece newPiece = board[x][y];

		if (newPiece.getColour() == this.getColour()) {
			System.out.format("Pawn IPC. Path not clear %n");
			return false;
		}
		return true;

	}

	public boolean isFirstMovePathClear(Piece[][] board, int x, int y) {
		boolean pathClear = false;
		int cX = this.getX();
		int xDiff = x - cX;
		Piece newPiece = board[x][y];

		if (newPiece.getColour() == this.getColour()) {
			return false;
		}

		if (this.getColour() == Colour.WHITE) {
			if (xDiff == 2) {
				Piece middle = board[x - 1][y];
				if (middle.getColour() == Colour.GREY) {
					pathClear = true;
				}
			}
		} else if (this.getColour() == Colour.BLACK) {
			if (xDiff == -2) {
				Piece middle = board[x + 1][y];
				if (middle.getColour() == Colour.GREY) {
					pathClear = true;
				}
			}
		}

		if (pathClear) {
			return pathClear;
		} else {
			System.out.print("Pawn IPC. Path not clear. ");
			return false;
		}

	}

	@Override
	public boolean makeTheMove(Piece[][] board, int x, int y) {
		boolean moved = false;
		int cX = this.getX();
		int cY = this.getY();
		Piece temp = board[x][y];
		Pawn pawn = this;
		board[x][y] = pawn;
		pawn.setX(x);
		pawn.setY(y);
		if ((pawn.getColour() == Colour.BLACK && pawn.getX() == 0)
				|| (pawn.getColour() == Colour.WHITE && pawn.getX() == 7)) {
			this.notifyPromoted();
		}

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

	// review cf isMoveLegal
	@Override
	public boolean canICheck(Piece[][] board, int x, int y) {
		boolean possible = false;
		int curX = this.getX();
		int curY = this.getY();
		Piece target = board[x][y];

		if (target.getClass() != King.class) {
			System.out.format("Checkmate target is not a King %n");
			return false;
		} else if ((this.getColour() == Colour.WHITE && target.getColour() != Colour.BLACK)
				|| (this.getColour() == Colour.BLACK && target.getColour() != Colour.WHITE)) {
			System.out.format("CheckMate target is not opposing colour %n");
			return false;
		}

		possible = isMoveLegal(board, x, y);

		System.out.format("Pawn %d:%d. Possible to check? %b %n", curX, curY, possible);
		return possible;
	}

	@Override
	public void infoAboutMove(int prevX, int prevY, int newX, int newY) {

		System.out.format("%s %s moved from %d:%d to %d:%d. ", this.getColour().toString(), this.toString(), prevX,
				prevY, newX, newY);

	}

	private void notifyPromoted() {
		System.out.format("Your pawn can be promoted. Call the promoted method with the "
				+ "type of piece you want it to be promoted to.");

	}

	public boolean promotePawn(Piece[][] board, Pawn pawn, Piece piece) {
		if (piece.getClass() == Pawn.class) {
			System.out.format("New piece is also a pawn. No promotion.");
			return false;
		}
		int x = pawn.getX();
		int y = pawn.getY();
		board[x][y] = piece;
		piece.setX(x);
		piece.setY(y);

		/*
		 * System.out.println(pawn.getColour() + " " + pawn.toString() +
		 * " promoted to " + piece.getColour() + " " + piece.toString() +
		 * " in board position " + piece.getX() + ":" + piece.getY());
		 */
		System.out.format("%s  %s  promoted to  %s %s %s in board position %d %d.", pawn.getColour(), pawn.toString(),
				piece.getColour(), piece.toString(), piece.getX(), piece.getY());

		pawn = null;
		return true;

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

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	@Override
	public String toString() {
		return "Pawn".toUpperCase();
	}

}
