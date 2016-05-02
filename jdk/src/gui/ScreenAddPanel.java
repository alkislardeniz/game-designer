package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Contains buttons to add screens to and delete screens from the game.
 * @author  Ata Deniz Aydin
 * @author  Deniz Alkislar
 * @version 28/04/16
 */
public class ScreenAddPanel extends JPanel
{
    GameEditController controller;

    public ScreenAddPanel(GameEditController controller)
    {
        JLabel label;

        this.controller = controller;

        setLayout(new GridLayout(5, 1));
        setPreferredSize(new Dimension(150, 125));

        label = new JLabel("Screen options");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        JButton button = new JButton("Add playable screen");
        button.addActionListener(new PlayableListener());
        add(button);

        button = new JButton("Add assignment screen");
        button.addActionListener(new AssignListener());
        add(button);

        button = new JButton("Add conditional screen");
        button.addActionListener(new CondListener());
        add(button);

        JRadioButton radio = new JRadioButton("Delete screen");
        radio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.deleting = radio.isSelected();
            }
        });
        radio.setEnabled(true);
        add(radio);
    }

    class PlayableListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // create dialog to create a playable screen
            JPanel panel = new JPanel();
            JTextField name;

            name = new JTextField();
            name.setEditable(true);

            panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

            panel.add(new JLabel("Name: "));
            panel.add(name);

            if (JOptionPane.showConfirmDialog(null, panel, "Create playable screen", JOptionPane.OK_CANCEL_OPTION)
                    == JOptionPane.OK_OPTION)
            {
                controller.createPlayableScreen(name.getText());
            }

            controller.repaint();
        }
    }

    class AssignListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // create dialog to create an assign screen
            JPanel panel = new JPanel();
            JTextField name, newValue;
            JComboBox variable, next;

            name = new JTextField();
            newValue = new JTextField();
            variable = new JComboBox<>(new Vector<>(controller.game.getVariables()));
            next = new JComboBox<>(new Vector<>(controller.game.getScreenNames()));

            panel.setLayout(new GridLayout(4, 2));

            panel.add(new JLabel("Name: "));
            panel.add(name);
            panel.add(new JLabel("Variable: "));
            panel.add(variable);
            panel.add(new JLabel("Value: "));
            panel.add(newValue);
            panel.add(new JLabel("Next screen: "));
            panel.add(next);

            if (JOptionPane.showConfirmDialog(null, panel, "Create assignment screen", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                controller.createAssignScreen(name.getText(), variable.getSelectedItem().toString(),
                                              newValue.getText(), next.getSelectedItem().toString());
            }

            controller.repaint();

        }
    }

    class CondListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // create dialog to create a cond screen
            JPanel panel = new JPanel();
            JTextField name, pred;
            JComboBox trueScreen, falseScreen;

            name = new JTextField();
            pred = new JTextField();

            trueScreen  = new JComboBox<>(new Vector<>(controller.game.getScreenNames()));
            falseScreen = new JComboBox<>(new Vector<>(controller.game.getScreenNames()));

            panel.setLayout(new GridLayout(4, 2));

            panel.add(new JLabel("Name: "));
            panel.add(name);
            panel.add(new JLabel("Condition: "));
            panel.add(pred);
            panel.add(new JLabel("True screen: "));
            panel.add(trueScreen);
            panel.add(new JLabel("False screen: "));
            panel.add(falseScreen);

            if (JOptionPane.showConfirmDialog(null, panel, "Create conditional screen", JOptionPane.OK_CANCEL_OPTION)
                    == JOptionPane.OK_OPTION)
            {
                controller.createCondScreen(name.getText(), pred.getText(),
                                            trueScreen.getSelectedItem().toString(), falseScreen.getSelectedItem().toString());
            }

            controller.repaint();
        }
    }
}
