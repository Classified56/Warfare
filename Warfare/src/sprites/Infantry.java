package sprites;

public class Infantry extends Soldier
{
	public Infantry()
	{
		health = 10;
		maxHealth = 10;
		damage = 4;
		isAlive = true;
		name = "Infantry " + number;
		moves = 2;
		number++;
	}
}
