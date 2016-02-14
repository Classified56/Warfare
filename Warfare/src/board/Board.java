package board;

import sprites.*;
import java.util.Date;

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
		int stop = (int)(Math.random() * 12) + 12, i = 0;
		while(i < stop)
		{
			Date date = new Date();
			@SuppressWarnings("deprecation")
			double quotient = date.getSeconds();
			int spotX = (int)(Math.random() * Math.random() * 21) + 4, spotY = (int)(Math.random() * Math.random() * 20);
			if(locations[spotX][spotY] != null)
			{
				locations[spotX][spotY] = new Barrier(spotX, spotY);
				i++;
			}
		}
	}
	
	public static boolean isOccupied(int x, int y)
	{
		return locations[x][y] != null;
	}
}
