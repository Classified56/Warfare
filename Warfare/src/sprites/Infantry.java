package sprites;

public class Infantry extends Soldier
{
	public Infantry()
	{
		health = 10;
		maxHealth = 10;
		damage = 4;
		isAlive = true;
		occupied = true;
		inBarrier = false;
		name = "Infantry " + number;
		moves = 2;
		range = 7;
		number++;
	}
	
	public Infantry(String x)
	{
		health = 10;
		maxHealth = 10;
		damage = 4;
		isAlive = true;
		occupied = true;
		inBarrier = false;
		name = "Infantry " + number + ": " + x;
		moves = 2;
		range = 7;
		number++;
	}
	
	public Infantry(int x, int y)
	{
		health = 10;
		maxHealth = 10;
		damage = 4;
		isAlive = true;
		occupied = true;
		inBarrier = false;
		name = "Infantry " + number;
		moves = 2;
		position[0] = x;
		position[1] = y;
		range = 7;
		number++;
	}
}
