package function;

import java.awt.Graphics;
import board.*;
import sprites.*;

public class WarfareRunner
{
	
	public static void main(String[] args)
	{
		BoardImage.initializeFieldFrame();
		Board.generateBarriers();
//		Player[] players = {new Player(), new Player()};
//		System.out.println(players[0].getSoldier(0) + " : " + players[1].getSoldier(0));
//		players[0].getSoldier(0).attack(players[1].getSoldier(0));;
		BoardImage.makeVisible();
		
		
	}

}
