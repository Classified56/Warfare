import java.util.ArrayList;
import sprites.*;

public class Player
{
	private ArrayList<Soldier> squad = new ArrayList<Soldier>();
	public Player()
	{
		squad.add(new Infantry("Joe"));
	}
}
