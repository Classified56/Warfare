import java.awt.Graphics;

import board.*;
import sprites.*;

public class WarfareRunner
{
	private static Player[] players = {new Player(), new Player()};
	public static void main(String[] args)
	{
		BoardImage.initializeFieldFrame();
		BoardImage.makeVisible();
	}

}
