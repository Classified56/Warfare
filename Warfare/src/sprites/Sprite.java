package sprites;

/**
 * super class for all the different objects to be put on the board
 * 
 * @author Davis Ranney
 *
 */
public abstract class Sprite
{
	//first number is x coordinate, and second number is y coordinate
	protected int position[] = new int[2];
	protected boolean occupied = false;
	protected boolean barrier;
	
	public boolean getBarrier()
	{
		return barrier;
	}
	
	public int[] getPosition()
	{
		return position;
	}
	
	public boolean getOccupied()
	{
		return occupied;
	}
}
