package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Deniz Alkislar on 21.4.2016.
 */
public class EditScrollPane extends JPanel
{
    ScreenEditController parent;
    String selectedComponent;

    public EditScrollPane(ScreenEditController parent)
    {
        ButtonGroup group;
        JScrollPane scrollPane;
        JRadioButton iconButton;

        this.parent = parent;

        group = new ButtonGroup();
        setLayout(new GridLayout(11, 1));

        //Add buttons to group and panel

        for (ObjectIconView icon : ObjectIconView.values())
        {
            iconButton = new JRadioButton(icon.toString());
            iconButton.setName(icon.toString());
            iconButton.addActionListener(new ButtonListener());
            group.add(iconButton);
        }

        //Add scroll bar to this panel
        scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 264));
    }

    public String getSelectedComponent()
    {
        return selectedComponent;
    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton selectedButton = (JButton) e.getSource();
            selectedComponent = selectedButton.getName();
            parent.updateSelectedComponent(selectedComponent);
        }
    }
}
