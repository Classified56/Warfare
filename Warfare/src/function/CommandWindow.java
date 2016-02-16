package function;

import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import sprites.*;
import board.*;

public class CommandWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame command = new JFrame("Warfare: Command Window");
	private final JPanel panelWin = new JPanel();
	public int gameMode;
	private HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>(3);
	private int player;
	private int turns;
	private boolean going;
	private int i;
	
	public CommandWindow()
	{
		command.setSize(500, 400);
		command.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		command.setResizable(false);
		command.setLocation(10, 40);
		dict.put(0, -1);
		dict.put(1, 1);
		dict.put(2, 0);
	}
	
	public void setInvisible()
	{
		command.setVisible(false);
		panelWin.removeAll();
		command.dispose();
	}
	
	public void chooseGameMode()
	{
		String[] gameModes = {"Team Death Match", "Capture Flags", "Alamo"};
		JButton enter = new JButton("Enter");
		JComboBox<String> dropdown = new JComboBox<String>(gameModes);
		JLabel words = new JLabel("Please select a game mode. ");
		command.add(panelWin);
		panelWin.add(words);
		panelWin.add(dropdown);
		panelWin.add(enter);
		gameMode = -1;
		going = true;
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				gameMode = dropdown.getSelectedIndex();
				switch(gameMode)
				{
					case 0:
						i = 1;
						playTD();
						break;
					case 1: 
						playCF();
						break;
					case 2:
						playA();
						break;
					default:
						break;
				}
			}
		});

		command.setVisible(true);
	}
	
	private void play()
	{
		switch(gameMode)
		{
			case 0:
				playTD();
				break;
			case 1:
				playCF();
				break;
			case 2:
				playA();
				break;
			default:
				break;
		}
	}
	
	private void playTD()
	{
		if(WarfareRunner.players[0].getSquadSize() == 0)
		{
			JOptionPane.showMessageDialog(panelWin, "Player 2 has won! Ending game. ");
			try
			{
				Thread.sleep(4000);
			} 
			catch (InterruptedException e)
			{}
			System.exit(0);
		}
		else if(WarfareRunner.players[1].getSquadSize() == 0)
		{
			JOptionPane.showMessageDialog(panelWin, "Player 1 has won! Ending game. ");
			try
			{
				Thread.sleep(6000);
			} 
			catch (InterruptedException e)
			{}
			System.exit(0);
		}
		i = i == 0 ? 1 : 0;
		WarfareRunner.cmdWin.setInvisible();
		WarfareRunner.cmdWin = new CommandWindow();
		player = i;
		doPlayer(i);
	}
	
	private void doPlayer(int i)
	{
		JOptionPane.showMessageDialog(panelWin, "Player " + (i + 1) + "'s turn. ");
		turns = WarfareRunner.players[i].getSquadSize();
		for (Soldier s : WarfareRunner.players[i].getSoldiers())
		{
			s.resetMoves();
			going = true;
			soldierChoice(s);
		}
	}
	
	private void soldierChoice(Soldier s)
	{
		JLabel x = new JLabel(s.toString());
		String[] actions = {"Move", "Attack", "Sit Tight"};
		JComboBox<String> dropdown = new JComboBox<String>(actions);
		JButton enter = new JButton("Enter");
		panelWin.add(x);
		panelWin.add(dropdown);
		panelWin.add(enter);
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int choice = dropdown.getSelectedIndex();
				switch(choice)
				{
					case 0:
						moveSoldier(s);
						if(s.getMoves() <= 1)
						{
							panelWin.remove(x);
							panelWin.remove(dropdown);
							panelWin.remove(enter);
							resetPanel();
						}
						break;
					case 1:
						s.setMoves(0);
						attackSoldier(s);
						panelWin.remove(x);
						panelWin.remove(dropdown);
						panelWin.remove(enter);
						resetPanel();
						break;
					default:
						s.sitTight();
						panelWin.remove(x);
						panelWin.remove(dropdown);
						panelWin.remove(enter);
						resetPanel();
						if(!going)
							play();
						break;
				}
			}
		});
		panelWin.setLayout(new BoxLayout(panelWin, BoxLayout.Y_AXIS));
		command.setVisible(true);
	}
	
	private void moveSoldier(Soldier s)
	{
		command.setVisible(false);
		JFrame move = new JFrame("Movement Window");
		move.setSize(500, 100);
		move.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		move.setResizable(false);
		move.setLocation(15, 600);
		JPanel panel = new JPanel();
		move.add(panel);
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
				BoardImage.initializeFieldFrame();
				move.setVisible(false);
				move.dispose();
				command.setVisible(true);
				if(!going)
					play();
				
			}
		});
		move.toFront();
		move.setVisible(true);
	}
	
	private void resetPanel()
	{
		command.setVisible(false);
		command.setVisible(true);
		turns--;
		if(turns == 0)
			going = false;
	}
	
	private void attackSoldier(Soldier s)
	{
		command.setVisible(false);
		JFrame attackWin = new JFrame("Attack Window");
		attackWin.setSize(500, 100);
		attackWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		attackWin.setResizable(false);
		attackWin.setLocation(15, 500);
		JPanel panel = new JPanel();
		attackWin.add(panel);
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
				attackWin.setVisible(false);
				attackWin.dispose();
				if(!going)
					play();
			}
		});
		attackWin.toFront();
		attackWin.setVisible(true);
	}
	
	public void playCF()
	{
		
	}
	
	public void playA()
	{
		
	}

	
	
}
