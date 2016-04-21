package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 21.4.2016.
 */
public class EditScrollPane extends JPanel {

    ButtonGroup group;
    JScrollPane scrollPane;
    JRadioButton iconButton;

    public EditScrollPane () //Objects scroll
    {
        group = new ButtonGroup ();
        setLayout (new GridLayout (11, 1));

        //Add buttons to group and panel

        for (ObjectIconView icon : ObjectIconView.values())
        {
            iconButton = new JRadioButton (icon.toString());
            iconButton.addActionListener(new ButtonListener());
            group.add(iconButton);
        }

        //Add scroll bar to this panel
        scrollPane = new JScrollPane (this);
        scrollPane.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize (new Dimension (100, 264));
    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }

    }
}
