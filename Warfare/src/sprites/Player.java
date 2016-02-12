package sprites;

import java.util.ArrayList;

public class Player
{
	private ArrayList<Soldier> squad = new ArrayList<Soldier>();
	public Player()
	{
		for (int i = 0; i < 5; i++)
		{
			int x = (int)(Math.random() * 30), y = (int)(Math.random() * 20);
			if(!board.Board.isOccupied(x, y));
				squad.add(new Infantry(x, y));
		}
	}
	
	public int[][] getSoldierLocations()
	{
		int positions[][] = new int[squad.size()][2];
		for (int i = 0; i < positions.length; i++)
		{
			positions[i] = squad.get(i).getPosition();
		}
		return positions;
	}
	
	
	/**
	 * 
	 * @param x : the location number for the soldier
	 * @return the soldier given
	 */
	public Soldier getSoldier(int x)
	{
		return squad.get(x);
	}
	
	public Soldier getSoldier(String s)
	{
		for (int i = 0; i < squad.size(); i++)
		{
			if(squad.get(i).name.equals(s))
			{
				return squad.get(i);
			}
		}
		return null;
	}
	
	
}
