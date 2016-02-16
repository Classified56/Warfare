package function;

import javax.swing.JOptionPane;

import board.*;
import sprites.*;

public class WarfareRunner
{
	public static Player[] players = {new Player(), new Player()};
	public static CommandWindow cmdWin;
	public static void main(String[] args)
	{
		startUp();
	}
	
	private static void startUp()
	{
		BoardImage.initializeFieldFrame();
		Board.generateBarriers();
		cmdWin = new CommandWindow();
		cmdWin.chooseGameMode();
	}
	
	public static void gameMode()
	{
		switch(cmdWin.gameMode)
		{
			case 0:
				playTD();
				break;
			case 1: 
				cmdWin.playCF();
				break;
			case 2:
				cmdWin.playA();
				break;
		}
	}
	
	private static void playTD()
	{
		cmdWin= new CommandWindow();
		cmdWin.playTD();
	}

}
