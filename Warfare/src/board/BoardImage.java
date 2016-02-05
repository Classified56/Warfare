package board;

import java.awt.*;
import javax.swing.*;


/**
 * creates the graphical image of the board
 * 
 * @author Davis Ranney
 * 
 */
public class BoardImage
{
	public static final JFrame fieldFrame = new JFrame("Warfare: Battlefield");
	
	public static void initializeFieldFrame()
	{
		fieldFrame.setSize(400, 100);
		fieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fieldFrame.setResizable(false);
		fieldFrame.setLocationRelativeTo(null);
	}
	
	public static void makeVisible()
	{
		fieldFrame.setVisible(true);
	}
}
