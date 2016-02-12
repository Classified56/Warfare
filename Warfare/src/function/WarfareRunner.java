package function;

import java.awt.Graphics;
import board.*;
import sprites.*;

public class WarfareRunner
{
	public static Player[] players = {new Player(), new Player()};
	public static void main(String[] args)
	{
		BoardImage.initializeFieldFrame();
		BoardImage.makeVisible();
		//System.out.println(players[0].getSoldier(3));
	}

}
