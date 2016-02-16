package sprites;

import javax.swing.JOptionPane;

import board.Board;
import board.BoardImage;
import function.WarfareRunner;
/**
 * super class for all the different types of soldiers
 * 
 * @author Davis Ranney
 *
 */
public class Soldier extends Sprite
{
	public static int number;
	public int idNumber;
	private int health;
	private int maxHealth;
	private String name;
	//each object gets two moves per turn
	private int moves;
	//damage done when object attacks
	private int damage;
	//reduces damage take if true
	private boolean inBarrier;
	private boolean isAlive;
	private int range;
	public static int teamNumber;
	
	/**
	 * a move method for all soldiers
	 * @param x 0-29
	 * @param y 0-19
	 */
	
	public Soldier(int x, int y)
	{
		health = 10;
		damage = 4;
		isAlive = true;
		inBarrier = false;
		barrier = false;
		name = "Soldier " + number;
		idNumber = number;
		moves = 3;
		position[0] = x;
		position[1] = y;
		range = 8;
		flag = false;
		number++;
	}
	public Soldier(int x, int y, int h)
	{
		health = h;
		damage = 4;
		isAlive = true;
		inBarrier = false;
		barrier = false;
		name = "Soldier " + number;
		idNumber = number;
		moves = 3;
		position[0] = x;
		position[1] = y;
		range = 8;
		flag = false;
		number++;
	}
	
	public void move(int x, int y)
	{
		//checks for valid moves from the objects current position
		if((x <= position[0] + 1 && x >= position[0] - 1) && (y <= position[1] + 1 && y >= position[1] - 1))
		{
			if(x >= 30)
				x = 29;
			else if(x < 0)
				x = 0;
			if(y >= 20)
				y = 19;
			else if(y < 0)
				y = 0;
			if(Board.field[x][y] == null)
			{
				moves--;
				position[0] = x;
				position[1] = y;
				inBarrier = Board.locations[x][y] != null && Board.locations[x][y].barrier ? true : false;
				if(Board.locations[x][y] != null && Board.locations[x][y].flag)
				{
					if(WarfareRunner.players[0].getSoldier(name) != null)
					{
						WarfareRunner.players[0].setScore(WarfareRunner.players[0].getScore() + 1);
						JOptionPane.showMessageDialog(null, name + " picked up a flag. Score: " + WarfareRunner.players[0].getScore());
					}
					else
					{
						WarfareRunner.players[1].setScore(WarfareRunner.players[1].getScore() + 1);
						JOptionPane.showMessageDialog(null, name + " picked up a flag. Score: " + WarfareRunner.players[1].getScore());
					}
					Board.locations[x][y] = null;
				}
				JOptionPane.showMessageDialog(null, name + " moved to position (" + x + ", " + y + ") \nMoves Remaining: " + moves);
				Board.updateSoldiers();
			}
			else
				JOptionPane.showMessageDialog(null, "Spot occupied: [" + x + ", " + y + "]");
		}
		else
			JOptionPane.showMessageDialog(null, "Invalid move for " + name);
	}
	
	public void resetMoves()
	{
		moves = 3;
	}
	
	public void attack(Soldier x)
	{
		double x1 = x.getPosition()[0], y1 = x.getPosition()[1];
		double distance = Math.hypot(Math.abs(x1 - (double)position[0]), Math.abs(y1 - (double)position[1]));
		if(distance <= range && !x.inBarrier)
		{
			x.changeHealth(damage);
			JOptionPane.showMessageDialog(null, x.name + " was hit. His health is " + x.health);
		}
		else
		{
			int fred = x.inBarrier ? 1 : 2;
			boolean accuracy = (Math.random() * .5) + .56 < Math.pow(.92, (distance - range) / fred);
			if(accuracy)
			{
				x.changeHealth(damage);
				JOptionPane.showMessageDialog(null, x.name + " was hit. His health is " + x.health);
			}
			else
				JOptionPane.showMessageDialog(null, name + " missed " + x.name);
		}
		moves = 0;
		
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public boolean getLife()
	{
		return isAlive;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getMoves()
	{
		return moves;
	}
	
	public void setMoves(int x)
	{
		moves = x;
	}
	
	public void sitTight()
	{
		JOptionPane.showMessageDialog(null, name + " is holding up.");
		moves = 0;
	}
	
	public int getIDNumber()
	{
		return idNumber;
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
			JOptionPane.showMessageDialog(null, name + " has died. ");
			WarfareRunner.players[0].updateDead();
			WarfareRunner.players[1].updateDead();
			Board.updateSoldiers();
			BoardImage.initializeFieldFrame();
		}
	}
	
	@Override
	public String toString()
	{
		return name + ": [Health: " + health +"]";
	}
}
