import java.awt.*;

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

      final CheckerBoard board = new CheckerBoard ();

      frame.add (board, BorderLayout.CENTER);
      
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.setSize (600, 600);
      frame.setVisible (true);
   }
}


