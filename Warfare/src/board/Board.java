package board;

import sprites.*;

import function.WarfareRunner;

/**
 * object to contain all the sprites
 * 
 * @author Davis Ranney
 *
 */
public class Board
{
	public static Sprite locations[][] = new Sprite[30][20];
	public static Soldier field[][] = new Soldier[30][20];
	
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
	
	public static void generateAlamo()
	{
		for(int i = 0; i < 6; i++)
		{
			int y = i + 4;
			locations[i][y] = new Barrier(i, y);
		}
		locations[1][9] = new Flag(1, 9);
		for(int i = 5; i >= 0; i--)
		{
			int y = 14 - i;
			locations[i][y] = new Barrier(i, y);
		}
	}
	
	public static void generateFlags()
	{
		for (int i = 0; i < 10; i++)
		{
			int x = (int)(Math.random() * 21) + 4, y = (int)(Math.random() * 20);
			if(locations[x][y] == null)
				locations[x][y] = new Flag(x, y);
			else
				i--;
		}
	}
	
	public static boolean isEmpty(int x, int y)
	{
		return locations[x][y] == null;
	}
	
	public static void updateSoldiers()
	{
		for (int i = 0; i < field.length; i++)
		{
			for (int j = 0; j < field[i].length; j++)
			{
				field[i][j] = null;
			}
		}
		
		for (int i = 0; i < 2; i++)
		{
			for (Soldier soldier : WarfareRunner.players[i].getSoldiers())
			{
				field[soldier.getPosition()[0]][soldier.getPosition()[1]] = soldier;
			}
		}
	}
}
