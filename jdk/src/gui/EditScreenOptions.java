package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Deniz Alkislar on 21.4.2016.
 */
public class EditScreenOptions extends JPanel{

    JRadioButton showgrid, delete, add;
    ButtonGroup group;
    boolean isDelete;
    boolean showGrids;

    public EditScreenOptions ()
    {
        delete = new JRadioButton ("Delete");
        add = new JRadioButton ("Add");
        showgrid = new JRadioButton ("Show Grids");
        group = new ButtonGroup();
        showGrids = false;

        //Adding action listeners
        delete.addActionListener(new ButtonListener());
        add.addActionListener(new ButtonListener());
        showgrid.addActionListener(new ButtonListener());

        //Adding grouped buttons
        group.add (add);
        group.add (delete);
        setLayout (new GridLayout (1,3));

        //Adding buttons to panel
        add (showgrid);
        add (add);
        add (delete);
    }

    //getters and setters
    public boolean getIsDelete()
    {
        return isDelete;
    }

    public boolean isGridShow()
    {
        return showGrids;
    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == add)
            {
                isDelete = false;
            }
            else if (e.getSource() == delete)
            {
                isDelete = true;
            }
            else if (e.getSource() == showgrid)
            {
                showGrids = true;
            }
        }

    }
}