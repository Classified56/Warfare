package sprites;

import board.Board;
/**
 * super class for all the different types of soldiers
 * 
 * @author Davis Ranney
 *
 */
public abstract class Soldier extends Sprite
{
	public static int number;
	protected int health;
	protected int maxHealth;
	protected String name;
	//each object gets two moves per turn, except heavies, who get one
	protected int moves;
	//damage done when object attacks
	protected int damage;
	//reduces damage take if true
	protected boolean inBarrier;
	protected boolean isAlive;
	protected int range;
	
	/**
	 * a move method for all soldiers
	 * @param x 0-29
	 * @param y 0-19
	 */
	protected void move(int x, int y)
	{
		//checks for valid moves from the objects current position
		if((x < position[0] + 1 && x > position[0] - 1) && (y < position[1] + 1 && y > position[1] - 1))
		{
			if(x >= 30)
				x = 29;
			else if(x < 0)
				x = 0;
			if(y >= 20)
				y = 19;
			else if(y < 0)
				y = 0;
			position[0] = x;
			position[1] = y;
			inBarrier = Board.locations[x][y].barrier ? true : false;
			System.out.println(name + " moved to position (" + x + ", " + y + ")");
			moves--;
		}
		else
			System.out.println("Invalid move for " + name);
	}
	
	public void resetMoves()
	{
		moves = 2;
	}
	
	public void attack(Soldier x)
	{
		double x1 = x.getPosition()[0], y1 = x.getPosition()[1];
		double distance = Math.hypot(Math.abs(x1 - (double)position[0]), Math.abs(y1 - (double)position[1]));
		if(distance <= range && !x.inBarrier)
		{
			x.changeHealth(damage);
			System.out.println(x.name + " was hit. His health is " + x.health);
		}
		else
		{
			int fred = x.inBarrier ? 1 : 2;
			boolean accuracy = (Math.random() * .5) + .56 < Math.pow(.92, (distance - range) / fred);
			if(accuracy)
			{
				x.changeHealth(damage);
				System.out.println(x.name + " was hit. His health is " + x.health);
			}
			else
				System.out.println(name + " missed " + x.name);
		}
		
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public boolean getLife()
	{
		return isAlive;
	}
	
	/**
	 * @param x damage done, is negative if health pack is applied
	 */
	public void changeHealth(int x)
	{
		health -= x;
		if(health > maxHealth)
			health = maxHealth;
		else if(health <= 0)
		{
			health = 0;
			isAlive = false;
		}
	}
	
	@Override
	public String toString()
	{
		return name + ": [Health: " + health +"][Position: " + position[0] + ", " + position[1] + "]";
	}
}
