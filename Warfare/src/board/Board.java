package board;

import function.WarfareRunner;
import sprites.*;

/**
 * object to contain all the sprites
 * 
 * @author Davis Ranney
 *
 */
public class Board
{
	public static Sprite locations[][] = new Sprite[30][20];
	
	/**
	 * Creation of the battlefield. 
	 * 
	 * @param x sets the game mode (0-Team Death-match, 1-Base Defense, 2-Hostage Recovery)
	 * 
	 */
	
	public static void generateBarriers()
	{
		for(int i = 4; i < 25; i++)
		{
			int stop = (int)(Math.random() * 4);
			for(int x = 0; x < stop; x++);
			{
				int spotY = (int)(Math.random() * 20);
				if(locations[i][spotY] == null)
					locations[i][spotY] = new Barrier(i, spotY);
				
			}
		}
	}
	
	public static boolean isEmpty(int x, int y)
	{
		return locations[x][y] == null;
	}
	
	public static void updateSoldiers()
	{
		//remove soldiers
		for (int i = 0; i < locations.length; i++)
		{
			for (int j = 0; j < locations[0].length; j++)
			{
				if(locations[i][j] != null && !locations[i][j].getBarrier())
					locations[i][j] = null;
			}
		}
		
		//add soldiers
		for (int i = 0; i < 2; i++)
		{
			int[][] spots = WarfareRunner.players[i].getSoldierLocations();
			for (int j = 0; j < spots.length; j++)
				locations[spots[j][0]][spots[j][1]] = WarfareRunner.players[i].getSoldier(j);
		}
	}
}
