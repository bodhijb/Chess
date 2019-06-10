package chessDP;

import java.io.Serializable;

public class Spare implements Piece, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 389388530915087040L;
	private String name;
	private Colour colour;
	private int x;
	private int y;
	private Game activeGame;

	public Spare(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		activeGame = new ChessGame();
	}

	@Override
	public boolean isMoveLegal(Piece[][] board, int x, int y) {
		System.out.println("Spares cannot check moves");
		return false;

	}

	@Override
	public boolean isPathClear(Piece[][] board, int x, int y) {
		System.out.println("Spares cannot check if the path is clear");
		return false;

	}

	@Override
	public boolean makeTheMove(Piece[][] board, int x, int y) {
		System.out.println("Spares cannot initiate moves");
		return false;
	}

	@Override
	public void infoAboutMove(int prevX, int prevY, int newX, int newY) {
		System.out.println("Spares cannot initiate moves");
		return;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActiveGame(Game activeGame) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Spare";
	}

	@Override
	public boolean canICheck(Piece[][] board, int x, int y) {

		return false;
	}

}
