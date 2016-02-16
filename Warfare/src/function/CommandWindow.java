package function;

import java.awt.Event;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

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
		makeMenus();
	}
	
	private void makeMenus()
	{
		JMenuBar menuBar = new JMenuBar();
		command.setJMenuBar(menuBar); 
		menuBar.add(makeFileMenu());
		menuBar.add(makeHelpMenu());
	}
	
	private JMenu makeFileMenu()
	{
		JMenu menu = new JMenu("File");
		JMenuItem menuItem;
		menu.setMnemonic(KeyEvent.VK_F); 
		menuItem = new JMenuItem("New Game");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(new NewListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				Event.CTRL_MASK));
		menu.add(menuItem); 
		
		menuItem = new JMenuItem("Save Game");
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.addActionListener(new SaveListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
			Event.CTRL_MASK));
		menu.add(menuItem); 
		menuItem = new JMenuItem("Load Game");
		menuItem.setMnemonic(KeyEvent.VK_L);
		menuItem.addActionListener(new LoadListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				Event.CTRL_MASK));
			menu.add(menuItem); 
		return menu;
	}
	
	private JMenu makeHelpMenu()
	{
		JMenu menu = new JMenu("Help");
		JMenuItem menuItem = new JMenuItem("About Warfare");; 
		menu.setMnemonic(KeyEvent.VK_H);
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.addActionListener(new AboutListener());
		menu.add(menuItem);
		return menu;
	}
	
	private class NewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			WarfareRunner.cmdWin.setInvisible();
			WarfareRunner.startNewGame();
		}	
	}
	
	private class SaveListener implements ActionListener
	{
	public void actionPerformed(ActionEvent ae)
		{
			String game = "Gamemode ";
			switch(gameMode)
			{
				case 1:
					game += "CF\n";
					break;
				case 2:
					game += "A\n";
					break;
				default:
					game += "TD\n";
					break;
			}
			player = i;
			game += player + "\n";
			game += "Barrier ";
			for(int i = 0; i < 30; i++)
			{
				for (int j = 0; j < 20; j++)
				{
					if(Board.locations[i][j] != null && Board.locations[i][j].getBarrier())
						game += i + " " + j + " ";
				}
			}
			game += "\nFlag ";
			for(int i = 0; i < 30; i++)
			{
				for (int j = 0; j < 20; j++)
				{
					if(Board.locations[i][j] != null && Board.locations[i][j].getFlag())
						game += i + " " + j + " ";
				}
			}
			for(Player p : WarfareRunner.players)
			{
				game += "\nSoldiers " + p.getSquadSize() + " \n";
				for(Soldier s : p.getSoldiers())
				{
					game += s.getName() + " " + s.getHealth() + " " + s.getPosition()[0] + " " + s.getPosition()[1] + "\n";
				}
			}
			
			
			try
			{
				PrintStream oFile = new PrintStream("GameSave.txt");
				oFile.print(game);
				oFile.close();
				System.exit(0);
			} 
			catch (IOException ioe)
			{
				System.out.println("Unable to save. \n"+ ioe);
			}
		}
	}
	
	private class LoadListener implements ActionListener
	{
		@SuppressWarnings("resource")
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				WarfareRunner.players = null;
				Player[] z = {new Player(), new Player()};
				WarfareRunner.players = z;
				Scanner file = new Scanner(new File("GameSave.txt"));
				file.next();
				char game = file.nextLine().charAt(0);
				switch(game)
				{
					case 'C':
						gameMode = 1;
						break;
					case 'A':
						gameMode = 2;
						break;
					default:
						gameMode = 0;
						break;
				}
				player = file.nextInt();
				i = player == 0 ? 1 : 0;
				file.nextLine();
				Scanner line = new Scanner(file.nextLine());
				line.next();
				Board.locations = new Sprite[30][20];
				while(line.hasNext())
				{
					int x = line.nextInt(), y = line.nextInt();
					Board.locations[x][y] = new Barrier(x, y);
				}
				Scanner line2 = new Scanner(file.nextLine());
				line2.next();
				while(line2.hasNext())
				{
					int x = line2.nextInt(), y = line2.nextInt();
					Board.locations[x][y] = new Flag(x, y);
				}
				Board.field = new Soldier[30][20];
				for(int i = 0; i < 2; i++)
				{
					file.next();
					int length = file.nextInt();
					int spots[][] = new int[length][3];
					for (int a = 0; a < length; a++)
					{
							file.next();
							file.nextInt();
							spots[a][2] = file.nextInt();
							spots[a][0] = file.nextInt();
							spots[a][1] = file.nextInt();
					}
					WarfareRunner.players[i].setSoldierLocations(spots);
				}
				WarfareRunner.cmdWin.setInvisible();
				BoardImage.initializeFieldFrame();
				WarfareRunner.cmdWin = new CommandWindow();
				Board.updateSoldiers();
				file.close();
				play();
			} 
			catch (FileNotFoundException e1)
			{
				System.out.println("Load File not found. ");
				e1.printStackTrace();
			}
		}	
	}
	
	private class AboutListener implements ActionListener
	{
	public void actionPerformed(ActionEvent e)
		{
		JOptionPane.showMessageDialog(null, "Please read the Rules.txt file for a full list of the rules. \n"
				+ "It mostly says: \"THERE IS NO CRYING IN WARFARE!\"\n"
				+ "Author: Davis Ranney");
		}
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
				i = 1;
				switch(gameMode)
				{
					case 0:
						playTD();
						break;
					case 1:
						Board.generateFlags();
						BoardImage.initializeFieldFrame();
						playCF();
						break;
					case 2:
						i = 0;
						Board.generateAlamo();
						BoardImage.initializeFieldFrame();
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
		boolean end = true;
		for (int i = 0; i < Board.locations.length; i++)
		{
			for (int j = 0; j < Board.locations[0].length; j++)
			{
				if(Board.locations[i][j] != null && Board.locations[i][j].getFlag())
					end = false;
			}
		}
		if(end && WarfareRunner.players[0].getScore() > WarfareRunner.players[1].getScore())
		{
			JOptionPane.showMessageDialog(panelWin, "Player 1 has won! Ending game. ");
			try
			{
				Thread.sleep(4000);
			} 
			catch (InterruptedException e)
			{}
			System.exit(0);
		}
		else if(end && WarfareRunner.players[0].getScore() < WarfareRunner.players[1].getScore())
		{
			JOptionPane.showMessageDialog(panelWin, "Player 2 has won! Ending game. ");
			try
			{
				Thread.sleep(6000);
			} 
			catch (InterruptedException e)
			{}
			System.exit(0);
		}
		else if(end && WarfareRunner.players[0].getScore() == WarfareRunner.players[1].getScore())
		{
			JOptionPane.showMessageDialog(panelWin, "Tie Game!");
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
	
	public void playA()
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
		if(WarfareRunner.players[0].getScore() > 0 || WarfareRunner.players[1].getScore() > 0)
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
		i = i == 0 ? 1 : 0;
		WarfareRunner.cmdWin.setInvisible();
		WarfareRunner.cmdWin = new CommandWindow();
		player = i;
		doPlayer(i);
	}

	
	
}
