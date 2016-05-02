package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Scrollable pane containing objects to add to a screen.
 * @author  Deniz Alkislar
 * @version 21/04/16
 */
public class EditScrollPaneRight extends JPanel
{
    ScreenEditController parent;
    String selectedComponent;

    public EditScrollPaneRight(ScreenEditController parent)
    {
        ButtonGroup group;
        JScrollPane scrollPane;
        JRadioButton iconButton;
        JPanel scrollPanel;

        this.parent = parent;

        group = new ButtonGroup();
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

        //Add buttons to group and panel
        for (ObjectIconView icon : ObjectIconView.values())
        {
            if (!icon.movable)
            {
                if (icon.defaultImage.getIconHeight() <= 50 && icon.defaultImage.getIconWidth() <= 50)
                    iconButton = new JRadioButton(icon.toString(), icon.defaultImage);
                else
                    iconButton = new JRadioButton(icon.toString());

                iconButton.setName(icon.toString());
                iconButton.addActionListener(new ButtonListener(icon.icon));

                iconButton.setMinimumSize(new Dimension(50, 50));
                group.add(iconButton);
                scrollPanel.add(iconButton);
            }
        }

        //Add scroll bar to this panel
        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(150, 500));

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 500));
        add(scrollPane);
    }

    public String getSelectedComponent()
    {
        return selectedComponent;
    }

    class ButtonListener implements ActionListener
    {
        ObjectIcon icon;

        public ButtonListener(ObjectIcon icon)
        {
            this.icon = icon;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            parent.setSelectedComponent(new ScreenObject(parent.screen, icon.toString(), icon));
        }
    }
}
