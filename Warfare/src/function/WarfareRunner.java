package function;

import java.awt.Graphics;
import board.*;
import sprites.*;

public class WarfareRunner
{
	
	public static void main(String[] args)
	{
//		BoardImage.initializeFieldFrame();
//		BoardImage.makeVisible();
//		Board.generateBarriers();
//		Player[] players = {new Player(), new Player()};
//		System.out.println(players[0].getSoldier(0) + " : " + players[1].getSoldier(0));
//		players[0].getSoldier(0).attack(players[1].getSoldier(0));;
		Infantry a = new Infantry(0, 0), b = new Infantry(10, 0);
		for (int i = 0; i < 20; i++)
		{
			a.attack(b);
		}
		
	}

}
