package function;


import board.*;
import sprites.*;

public class WarfareRunner
{
	public static Player[] players = {new Player(), new Player()};
	public static CommandWindow cmdWin;
	public static void main(String[] args)
	{
		startUp();
		play();
	}
	
	private static void startUp()
	{
		BoardImage.initializeFieldFrame();
		Board.generateBarriers();
		cmdWin = new CommandWindow();
		Board.updateSoldiers();
	}
	
	private static void play()
	{
		cmdWin.chooseGameMode();
	}

}
