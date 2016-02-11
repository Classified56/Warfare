package sprites;

import java.util.ArrayList;

public class Player
{
	private ArrayList<Soldier> squad = new ArrayList<Soldier>();
	public Player()
	{
		for (int i = 0; i < 5; i++)
		{
			squad.add(new Infantry());
		}
	}
	
	public int[][] getSoldierLocations()
	{
		int positions[][] = new int[5][2];
		for (int i = 0; i < positions.length; i++)
		{
			positions[i] = squad.get(i).getPosition();
		}
		return positions;
	}
}
