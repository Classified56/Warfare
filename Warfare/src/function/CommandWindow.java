package function;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import sprites.*;

public class CommandWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame command = new JFrame("Warfare: Command Window");
	private final JPanel panel = new JPanel();
	public int gameMode;
	private HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>(3);
	private int player;
	
	public CommandWindow()
	{
		command.setSize(500, 300);
		command.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		command.setResizable(true);
		command.setLocation(10, 40);
		dict.put(0, -1);
		dict.put(1, 1);
		dict.put(2, 0);
	}
	
	public void setVisible()
	{
		command.setVisible(true);
	}
	
	public void chooseGameMode()
	{
		String[] gameModes = {"Team Death Match", "Capture Flags", "Alamo"};
		JButton enter = new JButton("Enter");
		JComboBox<String> dropdown = new JComboBox<String>(gameModes);
		JLabel words = new JLabel("Please select a game mode. ");
		command.add(panel);
		panel.add(words);
		panel.add(dropdown);
		panel.add(enter);
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				gameMode = dropdown.getSelectedIndex();
				WarfareRunner.gameMode();
			}
		});

		command.setVisible(true);
	}
	
	
	public void playTD()
	{
		WarfareRunner.cmdWin = new CommandWindow();
		for(int i = 0; i < 2; i++)
		{
			player = i;
			JOptionPane.showMessageDialog(panel, "Player " + (i + 1) + "'s turn. ");
			for (Soldier s : WarfareRunner.players[i].getSoldiers())
			{
				soldierChoice(s);
			}
		}
	}
	
	private void soldierChoice(Soldier s)
	{
		JLabel x = new JLabel(s.toString());
		String[] actions = {"Move", "Attack", "Sit Tight"};
		JComboBox<String> dropdown = new JComboBox<String>(actions);
		JButton enter = new JButton("Enter");
		panel.add(x);
		panel.add(dropdown);
		panel.add(enter);
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int choice = dropdown.getSelectedIndex();
				switch(choice)
				{
					case 0:
						moveSoldier(s);
						break;
					case 1:
						attackSoldier(s);
						break;
					default:
						JOptionPane.showMessageDialog(panel, "He did nothing.");
						break;
				}
			}
		});
		command.setVisible(true);
	}
	
	private void moveSoldier(Soldier s)
	{
		JLabel movement = new JLabel("Choose movement. First Box: Vertical | Second Box: Horizontal");
		String[] y = {"Up", "Down", "No"};
		String[] x = {"Left", "Right", "No"};
		JComboBox<String> dd1 = new JComboBox<String>(y);
		JComboBox<String> dd2 = new JComboBox<String>(x);
		JButton enter = new JButton("Enter");
		panel.add(movement);
		panel.add(dd1);
		panel.add(dd2);
		panel.add(enter);
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int x = s.getPosition()[0] + dict.get(dd1.getSelectedIndex());
				int y = s.getPosition()[1] + dict.get(dd2.getSelectedIndex());
				s.move(x, y);
			}
		});
		command.setVisible(true);
	}
	
	private void attackSoldier(Soldier s)
	{
		int team = player == 0 ? 1 : 0;
		Soldier[] squad = WarfareRunner.players[team].getSoldiers();
		String[] enemies = new String[squad.length];
		for (int i = 0; i < enemies.length; i++)
		{
			enemies[i] = squad[i].toString();
		}
		JLabel attack = new JLabel("Choose which soldier to attack.");
		JComboBox<String> dd1 = new JComboBox<String>(enemies);
		panel.add(attack);
		panel.add(dd1);
		JButton enter = new JButton("Enter");
		panel.add(enter);
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				s.attack(WarfareRunner.players[team].getSoldier(dd1.getSelectedIndex()));
			}
		});
		command.setVisible(true);
	}
	
	public void playCF()
	{
		
	}
	
	public void playA()
	{
		
	}

	
	
}
