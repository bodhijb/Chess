package chessDP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGame extends Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3749537929397521846L;
	private Piece activePiece;
	private boolean firstMove;
	private static List<Map<Piece, Position>> startPos = new ArrayList<>();
	Piece[][] chessBoard;

	static {
		int both = 1;
		while (both++ <= 2) {
			Colour colour = Colour.WHITE;
			startPos.add(new HashMap<Piece, Position>());
			Piece add = new Rook("rookR", colour);
			add.setX(0);
			add.setY(0);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 0));
			startPos.add(new HashMap<Piece, Position>());
			add = new Rook("rookL", colour);
			add.setX(0);
			add.setY(7);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 7));
			startPos.add(new HashMap<Piece, Position>());
			add = new Knight("knightR", colour);
			add.setX(0);
			add.setY(1);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 1));
			startPos.add(new HashMap<Piece, Position>());
			add = new Knight("knightL", colour);
			add.setX(0);
			add.setY(6);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 6));
			startPos.add(new HashMap<Piece, Position>());
			add = new Bishop("bishopR", colour);
			add.setX(0);
			add.setY(2);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 2));
			startPos.add(new HashMap<Piece, Position>());
			add = new Bishop("bishopL", colour);
			add.setX(0);
			add.setY(5);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 5));
			startPos.add(new HashMap<Piece, Position>());
			add = new Queen("queen", colour);
			add.setX(0);
			add.setY(3);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 3));
			startPos.add(new HashMap<Piece, Position>());
			add = new King("king", colour);
			add.setX(0);
			add.setY(4);
			startPos.get(startPos.size() - 1).put(add, new Position(0, 4));

			Pawn pawn = null;
			for (int i = 0, j = 1; i < 8; i++) {
				startPos.add(new HashMap<Piece, Position>());
				pawn = new Pawn("pawn" + i, colour);
				pawn.setX(j);
				pawn.setY(i);
				startPos.get(startPos.size() - 1).put(pawn, new Position(j, i));
			}
			colour = Colour.BLACK;
			startPos.add(new HashMap<Piece, Position>());
			add = new Rook("rookL", colour);
			add.setX(7);
			add.setY(0);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 0));
			startPos.add(new HashMap<Piece, Position>());
			add = new Rook("rookR", colour);
			add.setX(7);
			add.setY(7);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 7));
			startPos.add(new HashMap<Piece, Position>());
			add = new Knight("knightL", colour);
			add.setX(7);
			add.setY(1);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 1));
			startPos.add(new HashMap<Piece, Position>());
			add = new Knight("knightR", colour);
			add.setX(7);
			add.setY(6);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 6));
			startPos.add(new HashMap<Piece, Position>());
			add = new Bishop("bishopL", colour);
			add.setX(7);
			add.setY(2);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 2));
			startPos.add(new HashMap<Piece, Position>());
			add = new Bishop("bishopR", colour);
			add.setX(7);
			add.setY(5);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 5));
			startPos.add(new HashMap<Piece, Position>());
			add = new Queen("queen", colour);
			add.setX(7);
			add.setY(3);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 3));
			startPos.add(new HashMap<Piece, Position>());
			add = new King("king", colour);
			add.setX(7);
			add.setY(4);
			startPos.get(startPos.size() - 1).put(add, new Position(7, 4));

			for (int i = 0, j = 6; i < 8; i++) {
				startPos.add(new HashMap<Piece, Position>());
				pawn = new Pawn("pawn" + i, colour);
				pawn.setX(j);
				pawn.setY(i);
				startPos.get(startPos.size() - 1).put(pawn, new Position(j, i));
			}

			colour = Colour.GREY;
			for (int i = 2; i < 6; i++) {
				for (int j = 0; j < 8; j++) {
					startPos.add(new HashMap<Piece, Position>());
					add = new Spare("spare", colour);
					add.setX(i);
					add.setY(j);
					startPos.get(startPos.size() - 1).put(add, new Position(i, j));
				}
			}

		}

	}

	public ChessGame() {
		chessBoard = new Piece[8][8];
		firstMove = true;
	}

	@Override
	public void startGame() {
		System.out.format("White player starts the game %n");
	}

	public Piece[][] fillBoard(Piece[][] board, List<Map<Piece, Position>> startPos) {
		for (int i = 0; i < startPos.size(); i++) {
			Map<Piece, Position> temp = startPos.get(i);
			for (Piece piece : temp.keySet()) {
				int x = temp.get(piece).getX();
				int y = temp.get(piece).getY();
				board[x][y] = piece;
			}
		}
		return board;
	}

	public void printBoard() {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < chessBoard.length; i++) {
			for (int j = 0; j < chessBoard[i].length; j++) {
				Piece temp = chessBoard[i][j];
				char abbrevColour = temp.getColour().toString().toLowerCase().charAt(0);
				/*
				 * System.out.printf("| %1$-7s: %2$d:%3$d ", abbrevColour + "" +
				 * chessBoard[i][j].toString(), i, j);
				 */
				System.out.printf("| %1$-7s: %2$d:%3$d ",
						temp.getClass() == Spare.class ? "~" : abbrevColour + "" + chessBoard[i][j].toString(), i, j);
			}
			System.out.print("|" + "\n");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------");
		}
		System.out.println();
	}

	@Override
	public boolean checkMoveLegalAndMove(Piece piece, int x, int y) {
		return piece.isMoveLegal(chessBoard, x, y) && movePieces(piece, x, y);

	}

	@Override
	public boolean checkFirstMoveLegalAndMove(Pawn pawn, int x, int y) {
		if (!pawn.getClass().equals(Pawn.class)) {
			return false;
		} else {
			return pawn.isFirstMoveLegal(chessBoard, x, y) && movePieces(pawn, x, y);
		}

	}

	private boolean movePieces(Piece piece, int x, int y) {
		return piece.makeTheMove(chessBoard, x, y);
	}

	public boolean requestPromotePawn(Pawn pawn, Piece piece) {
		return pawn.promotePawn(chessBoard, pawn, piece);
	}

	public Piece getActivePiece() {
		return activePiece;
	}

	public void setActivePiece(Piece activePiece) {
		this.activePiece = activePiece;

	}

	public static List<Map<Piece, Position>> getStartPos() {
		return startPos;

	}

	public static void setStartPos(List<Map<Piece, Position>> startPos) {
		ChessGame.startPos = startPos;

	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	
	public Piece[][] getBoard() {
		return chessBoard;
	}

	@Override
	public void setBoard(Piece[][] board) {
		this.chessBoard = board;
	}

	
	

}
