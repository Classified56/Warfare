package board;

import java.awt.*;
import javax.swing.*;
import function.WarfareRunner;

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
		graphics.setColor(Color.white);
		for(int i = 1; i < 20; i++)
			graphics.drawLine(i * 30, 0, i * 30, 900);
		for(int i = 1; i < 30; i++)
			graphics.drawLine(0, i * 30, 606, i * 30);
		WarfareRunner.players
	}
}
