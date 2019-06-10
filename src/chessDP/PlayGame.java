package chessDP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Scanner scan = new Scanner(System.in);

		Game chess = new ChessGame();
		List<Map<Piece, Position>> start = ChessGame.getStartPos();
		Piece[][] board = chess.fillBoard(chess.getBoard(), start);
		Player wPlayer = new Player("White Player", Colour.WHITE); 
		Player bPlayer = new Player("Black Player", Colour.BLACK);
		wPlayer.setActiveGame(chess);
		bPlayer.setActiveGame(chess);
		board = chess.getBoard();
		System.out.println("New board");
		chess.printBoard();
		System.out.println(Player.getNewPiecesMap());
		chess.startGame();
		wPlayer.start();
		/*
		 * //bPlayer.start();
		 * 
		 * while(!wPlayer.amIChecked() && !bPlayer.amIChecked()) {
		 * 
		 * wPlayer.play(); bPlayer.play();
		 * 
		 * }
		 */

		scan.close();

	}

	public static void saveGame(File file, Game chess) throws IOException {

		OutputStream output = new FileOutputStream(file);

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(output)) {

			Game cGameOut = chess;
			cGameOut.setBoard(chess.getBoard());
			cGameOut.setFirstMove(chess.isFirstMove());

			objectOutputStream.writeObject(cGameOut);

		}

	}

	public static void resumeGame(File file, Game chess) throws IOException, ClassNotFoundException {
		InputStream input = new FileInputStream(file);

		try (ObjectInputStream objectInputStream = new ObjectInputStream(input)) {

			chess = (Game) objectInputStream.readObject();
		}

		System.out.format("chess in method %n");
		chess.printBoard();

	}

}
