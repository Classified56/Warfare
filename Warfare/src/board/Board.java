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
	public static Barrier locations[][] = new Barrier[30][20];
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
