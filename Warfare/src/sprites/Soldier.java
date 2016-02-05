package sprites;

/**
 * super class for all the different types of soldiers
 * 
 * @author Davis Ranney
 *
 */
public abstract class Soldier extends Sprite
{
	protected int health;
	protected int maxHealth;
	protected String name;
	//each object gets two moves per turn, except heavies, who get one
	protected int moves;
	//damage done when object attacks
	protected int damage;
	//reduces damage take if true
	protected boolean behindBarrier;
	protected boolean isAlive;
	
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
			moves--;
		}
		else
			System.out.println("Invalid move for soldier " + name);
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
}
