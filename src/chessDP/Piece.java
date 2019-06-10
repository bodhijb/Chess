package chessDP;

import java.io.Serializable;

public interface Piece extends Serializable {

	

	public boolean isMoveLegal(Piece[][] board, int x, int y);
	
	public boolean isPathClear(Piece[][] board, int x, int y);
	
	public boolean makeTheMove(Piece[][] board, int x, int y);
	
	public void infoAboutMove(int prevX, int prevY, int newX, int newY);
	
	public boolean canICheck(Piece[][] board, int x, int y);
	
	public String getName();
	
	public void setName(String name);
	
	public Colour getColour();
	
	public void setColour(Colour colour);

	public int getX();

	public int getY();

	public void setX(int x);

	public void setY(int y);
	
	public Game getActiveGame();
	
	public void setActiveGame(Game activeGame);
	

}
