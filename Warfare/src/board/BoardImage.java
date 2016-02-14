package board;

import java.awt.*;
import javax.swing.*;

/**
 * creates the graphical image of the board
 * 
 * @author Davis Ranney
 * 
 */
public class BoardImage extends Canvas
{
	public static final JFrame fieldFrame = new JFrame("Warfare: Battlefield");
	private static final long serialVersionUID = 1L;
	public static void initializeFieldFrame()
	{
		BoardImage image = new BoardImage();
		fieldFrame.setSize(606, 904);
		fieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fieldFrame.getContentPane().add(image).setBackground(Color.black);
		fieldFrame.setResizable(false);
		fieldFrame.setLocationRelativeTo(null);
	}
	
	public static void makeVisible()
	{
		fieldFrame.setVisible(true);
	}
	
	public void paint(Graphics graphics)
	{
		drawGrid(graphics);
		drawBarriers(graphics);
	}
	
	private void drawGrid(Graphics graphics)
	{
		graphics.setColor(Color.white);
		for(int i = 1; i < 20; i++)
			graphics.drawLine(i * 30, 0, i * 30, 900);
		for(int i = 1; i < 30; i++)
			graphics.drawLine(0, i * 30, 606, i * 30);
	}
	
	private void drawBarriers(Graphics graphics)
	{
		graphics.setColor(Color.red);
		for(int i = 0; i < Board.locations.length; i++)
		{
			for(int j = 0; j < Board.locations[0].length; j++)
			{
				if(Board.locations[i][j] != null && Board.locations[i][j].getBarrier())
					graphics.drawRect(j * 30, i * 30, 30, 30);
			}
		}
	}
}
