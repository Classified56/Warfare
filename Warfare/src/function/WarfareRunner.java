package function;


import board.*;
import sprites.*;

public class WarfareRunner
{
	public static Player[] players;
	public static CommandWindow cmdWin;
	public static void main(String[] args)
	{
		startNewGame();
	}
	
	public static void startNewGame()
	{
		Board.locations = new Sprite[30][20];
		Board.field = new Soldier[30][20];
		players = null;
		Player[] z = {new Player(), new Player()};
		players = z;
		BoardImage.initializeFieldFrame();
		Board.generateBarriers();
		cmdWin = new CommandWindow();
		Board.updateSoldiers();
		play();
	}
	
	private static void play()
	{
		cmdWin.chooseGameMode();
	}

}
