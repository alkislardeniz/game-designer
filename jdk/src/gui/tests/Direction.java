package gui.tests;

import javax.swing.*;
import java.awt.*;

/**
 * Mehmet was here lolz
 * @Deniz Alkýþlar
 * @Program Name 1.0
 * @malesef Ankara - 00.00.2016
 */

public class Direction
{
    public static void main( String[] args)
    {
        ImageIcon logo = new ImageIcon ("pics/logo.png");
        JFrame f = new JFrame ("Dadam - Game Designer");
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo (null);
        f.setSize (520, 620);

        f.add( new DirectionPanel() );
        f.add(new JLabel(logo), BorderLayout.NORTH);

        f.setVisible( true);
    }

} // end of class Direction
