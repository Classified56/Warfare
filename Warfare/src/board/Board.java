package board;

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
		for(int i = 0; i < (int)(Math.random() * 8) + 5; i++)
		{
			int spotX = (int)(Math.random() * 21) + 4, spotY = (int)(Math.random() * 20);
			locations[spotX][spotY] = new Barrier(spotX, spotY);
		}
	}
	
	public static Soldier getSoldier(int x, int y)
	{
		return (Soldier) locations[x][y];
	}
}
