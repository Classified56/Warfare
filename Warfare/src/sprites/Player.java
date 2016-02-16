package sprites;

import java.util.ArrayList;

public class Player
{
	private ArrayList<Soldier> squad = new ArrayList<Soldier>();
	public Player()
	{
		if(Soldier.teamNumber == 0)
		{
			squad.add(new Soldier(4, 9));
			squad.add(new Soldier(3, 8));
			squad.add(new Soldier(3, 10));
			squad.add(new Soldier(2, 7));
			squad.add(new Soldier(2, 11));
			Soldier.teamNumber++;
		}
		else
		{
			squad.add(new Soldier(25, 9));
			squad.add(new Soldier(26, 8));
			squad.add(new Soldier(26, 10));
			squad.add(new Soldier(27, 7));
			squad.add(new Soldier(27, 11));
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
	
	public void updateDead()
	{
		for (int i = 0; i < squad.size(); i++)
		{
			if(!squad.get(i).getLife())
			{
				squad.remove(i);
				i--;
			}
		}
	}
	
	public int getSquadSize()
	{
		return squad.size();
	}
	
	public void printBarrier()
	{
		for(int i = 0; i < squad.size(); i++)
			System.out.println(squad.get(i).position[0] + ", " + squad.get(i).position[1]);
	}
	
	/**
	 * 
	 * @param x : the location number for the soldier
	 * @return the soldier given
	 */
	public Soldier[] getSoldiers()
	{
		Soldier[] x = new Soldier[squad.size()];
		for (int i = 0; i < squad.size(); i++)
		{
			x[i] = squad.get(i);
		}
		return x;
	}
	
	public Soldier getSoldier(int x)
	{
		return squad.get(x);
	}
	
	public Soldier getSoldier(String s)
	{
		for (int i = 0; i < squad.size(); i++)
		{
			if(squad.get(i).getName().equals(s))
			{
				return squad.get(i);
			}
		}
		return null;
	}
	
	
}
