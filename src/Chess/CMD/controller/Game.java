package Chess.CMD.controller;

import Chess.CMD.model.Bishop;
import Chess.CMD.model.ChessPiece;
import Chess.CMD.model.PlayerColor;
import Chess.CMD.model.King;
import Chess.CMD.model.Knight;
import Chess.CMD.model.Pawn;
import Chess.CMD.model.Player;
import Chess.CMD.model.Queen;
import Chess.CMD.model.Rook;
import Chess.CMD.model.Square;

public class Game {

	private Square[][] board;
	private ChessPiece[] capturedWhite;
	private ChessPiece[] capturedBlack;
	private int capturedWhiteCount;
	private int capturedBlackCount;
	private Player black;
	private Player white;
	private Player turn;
	private boolean blackInCheck;
	private boolean whiteInCheck;

	public Game() {

		board = new Square[8][8];
		black = new Player(PlayerColor.BLACK);
		white = new Player(PlayerColor.WHITE);
		black.setOpponent(white);
		white.setOpponent(black);
		blackInCheck = false;
		whiteInCheck = false;
		capturedWhite = new ChessPiece[16];
		capturedBlack = new ChessPiece[16];
		capturedWhiteCount = 0;
		capturedBlackCount = 0;
		turn = white;

		for (int i = 0; i < 8 ; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Square(i, j);
			}
		}

		placePieces(black);
		placePieces(white);


	}

	private void placePieces(Player player) {

		int row = player.getColor() == PlayerColor.WHITE ? 7: 0;

		ChessPiece[] pieces = new ChessPiece[]
				{ new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()};


		player.setKing((King)pieces[4]);

		for (int i = 0; i < 8; i++) {
			pieces[i].setLocation(board[row][i]);
			pieces[i].setBoard(board);
			board[row][i].setPiece(pieces[i]);
			board[row][i].getPiece().setPlayer(player);
			player.addPiece(pieces[i]);

		}

		row = player.getColor() == PlayerColor.WHITE ? 6 : 1;

		for (int i = 0; i < 8; i++) {
			ChessPiece pawn = new Pawn();
			pawn.setLocation(board[row][i]);
			pawn.setBoard(board);
			board[row][i].setPiece(pawn);
			board[row][i].getPiece().setPlayer(player);
			player.addPiece(pawn);
		}

	}

	public Square[][] getBoard() { return board; }

	public Player getCurrentPlayer() { return turn; } 

	public boolean move(String s, String d, String p) {

		blackInCheck = false;
		whiteInCheck = false;
		int c = fileToIndex(s.charAt(0));
		int r = 8 - Character.getNumericValue(s.charAt(1));


		Square source = board[r][c];

		c = fileToIndex(d.charAt(0));
		r = 8 - Character.getNumericValue(d.charAt(1));


		Square dest = board[r][c];

		ChessPiece sourcePiece = source.getPiece();

		ChessPiece destPiece = dest.getPiece();

		if (sourcePiece == null) return false;

		//attempting to move opponent's piece
		if (sourcePiece.getPlayer().getColor() != getCurrentPlayer().getColor()) return false;


		if (!sourcePiece.canMoveTo(dest)) return false;

		//capture
		if (destPiece != null) {

			destPiece.setLocation(null);
			if (turn == white) {

				capturedBlack[capturedBlackCount++] = destPiece;
			} 
			else {

				capturedWhite[capturedWhiteCount++] = destPiece;
			}
		}

		enPassant(sourcePiece, dest);


		source.setPiece(null);
		sourcePiece.setLocation(dest);
		dest.setPiece(sourcePiece);
		sourcePiece.incrementMoves();

		//promotion
		if (((turn == white && dest.getY() == 0) || (turn == black && dest.getY() == 7)) && sourcePiece instanceof Pawn) {

			ChessPiece promotedPiece = promotion(p);
			promotedPiece.setLocation(dest);
			promotedPiece.setNumberOfMoves(sourcePiece.numberOfMoves());
			promotedPiece.setPlayer(turn);
			promotedPiece.setBoard(board);
			sourcePiece = promotedPiece;
			dest.setPiece(sourcePiece);
		}

		Square oppKing = turn.getOpponent().getKing().getLocation();
		
		if (turn.getOpponent().getKing().inCheck(oppKing)) {

			if (turn.getColor() == PlayerColor.WHITE) {
				blackInCheck = true;
				if (black.getKing().checkmate(black.getKing().getLocation())) {
					System.out.println("White wins.");
					System.exit(0);
				}
			} else {
				whiteInCheck = true;
				if (white.getKing().checkmate(white.getKing().getLocation())) {
					System.out.println("Black wins.");
					System.exit(0);
				}
			}
		}


		turn = (turn == white) ? black : white;

		return true;
	}

	private void enPassant(ChessPiece sourcePiece, Square dest) {

		if (sourcePiece instanceof Pawn) {

			int yPos = sourcePiece.getPlayer().getColor() == PlayerColor.WHITE ? 1 : -1;


			if (Math.abs(sourcePiece.getLocation().getX() - dest.getX()) == 1 && sourcePiece.getLocation().getY() == dest.getY() + yPos) {

				ChessPiece capturedPawn = board[sourcePiece.getLocation().getY()][dest.getX()].getPiece();

				if (turn == white) {
					capturedBlack[capturedBlackCount++] = capturedPawn;
				}
				else {
					capturedWhite[capturedWhiteCount++] = capturedPawn;
				}

				board[sourcePiece.getLocation().getY()][dest.getX()].setPiece(null);
			}
		}
	}

	private int fileToIndex(char file) {

		switch (file) {

		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		default:
			return -1;
		}

	}

	public ChessPiece promotion(String p) {

		if (p == null) p = "Q";

		switch(p.charAt(0)) {

		case 'Q':
			return new Queen();
		case 'B':
			return new Bishop();
		case 'N':
			return new Knight();
		case 'R':
			return new Rook();
		default:
			return new Queen();
		}
	}

	public int blackCaptureCount() { return capturedBlackCount; }

	public int whiteCaptureCount() { return capturedWhiteCount; }

	public boolean blackInCheck() { return blackInCheck; }

	public boolean whiteInCheck() { return whiteInCheck; }



}
