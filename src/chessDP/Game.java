package chessDP;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class Game implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Piece[][] generalGameBoard = null;
	public String state;		
	
	public abstract void startGame();
	
	public abstract Piece[][] fillBoard(Piece[][] board, List<Map<Piece, Position>> startPos);
	
	public abstract void printBoard();

	public Piece[][] getBoard() {
		return generalGameBoard;
	}
	
	public abstract boolean checkMoveLegalAndMove(Piece piece, int x, int y);
	
	public abstract boolean checkFirstMoveLegalAndMove(Pawn piece, int x, int y);

	public void setBoard(Piece[][] board) {
		this.generalGameBoard = board;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public abstract boolean isFirstMove();
	
	public abstract void setFirstMove(boolean firstMove);
	
	
}
