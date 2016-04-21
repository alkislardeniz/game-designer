package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by USER on 21.4.2016.
 */
public class EditScreenOptions extends JPanel{

    JRadioButton showgrid, delete, add;
    ButtonGroup group;

    public EditScreenOptions ()
    {
        delete = new JRadioButton ("Delete");
        add = new JRadioButton ("Add");
        showgrid = new JRadioButton ("Show Grids");
        group = new ButtonGroup();

        //Adding grouped buttons
        group.add (add);
        group.add (delete);
        setLayout (new GridLayout (1,3));

        //Adding buttons to panel
        add (showgrid);
        add (add);
        add (delete);
    }
}