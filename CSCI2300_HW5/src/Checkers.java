import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
   A program that allows users to edit a scene composed
   of items.
*/
public class Checkers
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		Player p1 = new Player(1);
		Player p2 = new Player(2);
		final CheckerBoard board = new CheckerBoard (p1, p2);

		
		System.out.println("*************************");
		System.out.println("Player 1 piece count: " + p1.getCount());
		System.out.println("Player 2 piece count: " + p2.getCount());
		System.out.println("*************************");
		
		JButton piecesLeft = new JButton("Pieces Left");
		JLabel numPieces = new JLabel("");
		JPanel panel = new JPanel();
		panel.add(piecesLeft);
		
		piecesLeft.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent ae) {
				
				numPieces.setText("P1: " + p1.getCount() + " P2: " + p2.getCount());
				
				String[] options = {"Restart", "Exit"};
				
				panel.add(numPieces);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700, 600);
				frame.setVisible(true);
				
				p1.resetCount(); //******* Check Ending Operations ********
				
				if ((p1.getCount() == 0 )|| (p2.getCount() == 0 )) {
					int endingChoice = JOptionPane.showOptionDialog(null, 
												"Congratulations", 
												"Game Over", 
												JOptionPane.YES_NO_OPTION, 
												JOptionPane.INFORMATION_MESSAGE, 
												null, 
												options, 
												"Exit");
					if (endingChoice == 0)
						board.reset();
					if (endingChoice == 1) {
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						System.exit(0);
					}
				}
			}		
		});
		
		frame.add(panel, BorderLayout.EAST);

		frame.add (board, BorderLayout.CENTER);

		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setSize (650, 600);
		frame.setVisible (true);
	}
}

