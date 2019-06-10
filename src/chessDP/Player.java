package chessDP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {

	private String name;
	private Colour colour;
	private Game activeGame;
	private int noOfMoves;
	private int piecesTaken;
	private boolean firstMoveMade;
	private static boolean started = false;
	private static Map<String, Piece> newPiecesMap;
	private boolean checkMated;

	static {
		newPiecesMap = new HashMap<>();
		int t = 0;
		Colour col = Colour.WHITE;
		while (t++ < 2) {
			String colInd = col.toString().substring(0, 1);
			newPiecesMap.put("King" + "-" + colInd, new King("king", col));
			newPiecesMap.put("Queen" + "-" + colInd, new Queen("queen", col));
			newPiecesMap.put("Rook" + "-" + colInd, new Rook("rook", col));
			newPiecesMap.put("Knight" + "-" + colInd, new Knight("knight", col));
			newPiecesMap.put("Bishop" + "-" + colInd, new Bishop("bishop", col));
			newPiecesMap.put("Pawn" + "-" + colInd, new Pawn("pawn", col));
			col = Colour.BLACK;
		}

	}

	public Player(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		this.noOfMoves = 0;
		this.piecesTaken = 0;
		firstMoveMade = false;
		checkMated = false;
	}

	public Player() {

	}

	public void start() {
		if (!started) {
			if (this.getColour() != Colour.WHITE) {
				System.out.format("White player should start the game %n");
				return;
			}
		}

		Piece[][] board = getActiveGame().getBoard();
		Pawn pawn = (Pawn) getPieceToMove(board);
		if (this.getColour() != pawn.getColour()) {
			System.out.format("Player colour is different to pawn colour %n");
			return;
		}
		if (firstMoveMade) {
			System.out.format("Use play method");
			return;
		}

		int x = getDestinationRowNo();
		int y = getDestinationColNo();
		
		Piece temp = this.getActiveGame().getBoard()[x][y];
		System.out.println(temp.toString() + " " + temp.getClass() + " " + temp.getColour());
		boolean firstMoveOk = activeGame.checkFirstMoveLegalAndMove(pawn, x, y);
		System.out.format(firstMoveOk ? "Total number of moves = %d %n" : "%n", ++noOfMoves);
		if (firstMoveOk) {
			if (temp.getColour() != Colour.GREY && temp.getColour() != pawn.getColour()) {
				this.setPiecesTaken(getPiecesTaken() + 1);
				System.out.format("%s has taken %d pieces %n", this.getName(), getPiecesTaken());
			}
			this.getActiveGame().printBoard();
		}
		firstMoveMade = true;
		Player.started = true;

	}

	public void play() {
		Piece[][] board = getActiveGame().getBoard();
		Piece piece = getPieceToMove(board);

		if (this.getColour() != piece.getColour()) {
			System.out.format("Player colour different to pawn colour");
			return;
		}
		int x = getDestinationRowNo();
		int y = getDestinationColNo();

		Piece temp = this.getActiveGame().getBoard()[x][y];
		if (piece.getX() - x > Math.abs(1)) {
			if (firstMoveMade) {
				System.out.println("Piece's move is incorrect. Too far forward.");
				return;
			}
		}
		boolean moveOk = activeGame.checkMoveLegalAndMove(piece, x, y);
		System.out.format(moveOk ? "Total number of moves = %d %n" : "%n", ++noOfMoves);
		if (moveOk) {
			if (temp.getColour() != Colour.GREY && temp.getColour() != piece.getColour()) {
				this.setPiecesTaken(getPiecesTaken() + 1);
				System.out.format("%s %s has taken the %s %s. ", piece.getColour(), piece.toString(), temp.getColour(),
						temp.toString());
				System.out.format("%s %s has taken %d pieces %n", this.getColour(), this.toString(),
						this.getPiecesTaken());
			}
			this.getActiveGame().printBoard();
			System.out.println(this.getColour() == Colour.BLACK ? "White player's move. Call play() or amIChecked()"
					: "Black player's move. Call play() or amIChecked().");
			firstMoveMade = true;
		}

	}

	private Piece getPieceToMove(Piece[][] board) throws UnsupportedOperationException {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the type of piece to move, in this format: Pawn-W for a white pawn");
		String pieceStr = scan.nextLine();
		Piece pie = newPiecesMap.get(pieceStr);

		System.out.println("Enter the row number of the piece");
		int cX = scan.nextInt();
		System.out.println("Enter the column number of the piece");
		int cY = scan.nextInt();
		Piece piece = board[cX][cY];
		System.out.println(piece.toString() + " " + piece.getClass() + " " + piece.getColour());
		System.out.println(pie.toString() + " " + pie.getClass() + " " + pie.getColour());
		if (piece.getClass() != pie.getClass()) {
			scan.close();
			throw new UnsupportedOperationException(
					"The piece in this position if not a " + pieceStr + ". Please review");
		}
		scan.close();
		return piece;

	}

	private int getDestinationRowNo() throws UnsupportedOperationException {
		Scanner scan = new Scanner(System.in);
		int cX = 0;
		System.out.println("Enter the destination row number, between 0 and 7");
		if (scan.hasNextInt())
			cX = scan.nextInt();
		System.out.println(cX);
		/*
		 * if (cX < 0 || cX > 7) { scan.close(); throw new
		 * UnsupportedOperationException(
		 * "The row number should be in the range 0 - 7"); }
		 */

		scan.close();
		return cX;

	}

	private int getDestinationColNo() {
		Scanner scan = new Scanner(System.in);
		int cY = 0;
		System.out.println("Enter the destination column number, between 0 and 7");
		if (scan.hasNextInt())
			cY = scan.nextInt();
		System.out.println(cY);
		/*
		 * if (cY < 0 || cY > 7) { scan.close(); throw new
		 * UnsupportedOperationException(
		 * "The col number should be in the range 0 - 7"); }
		 */

		scan.close();
		return cY;

	}

	public boolean requestPromotePawn(Pawn pawn, Piece piece) {
		boolean promoted = ((ChessGame) activeGame).requestPromotePawn(pawn, piece);
		this.getActiveGame().printBoard();
		return promoted;

	}

	public boolean amIChecked() {
		System.out.println("To get the position of your King...");
		int x = getDestinationRowNo();
		int y = getDestinationColNo();

		Piece chased = activeGame.getBoard()[x][y];
		if (chased.getClass() != King.class) {
			return false;
		}

		boolean checked = false;
		Colour myColour = getColour();
		Piece[][] board = activeGame.getBoard();

		one: for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Piece checkPiece = board[i][j];
				if (checkPiece.getColour() != myColour && checkPiece.getClass() != Spare.class) {
					// System.out.println(checkPiece.getColour() + " " +
					// checkPiece.toString());
					checked = checkPiece.canICheck(board, x, y);
					// System.out.println("Player can check " + checked);
					if (checked) {
						break one;
					}
				}
			}
		}
		return checked;

	}

	public Game getActiveGame() {
		return activeGame;
	}

	public int getNoOfMoves() {
		return noOfMoves;
	}

	public void setActiveGame(Game activeGame) {
		this.activeGame = activeGame;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public int getPiecesTaken() {
		return piecesTaken;
	}

	public void setPiecesTaken(int piecesTaken) {
		this.piecesTaken = piecesTaken;
	}

	public static Map<String, Piece> getNewPiecesMap() {
		return newPiecesMap;
	}

	public static void setNewPiecesMap(Map<String, Piece> newPiecesMap) {
		Player.newPiecesMap = newPiecesMap;
	}

	@Override
	public String toString() {
		return "player".toUpperCase();
	}

}
