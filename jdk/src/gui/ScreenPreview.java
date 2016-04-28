package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;

/**
 * Displays preview image for screens, to be used inside GameEditController.
 * Mehmet was here on 26 April 2016
 * Created by admin on 4/26/16.
 */
public class ScreenPreview extends JPanel implements ScreenVisitor
{
    Screen screen;
    static final int WIDTH = 100;
    static final int HEIGHT = 75;

    JLabel nameLabel, textLabel;

    // for assignment screens, show the screen's name and assignment
    // for conditional screens, show its name and predicate
    // for playable screens, show its name
    public ScreenPreview(Screen screen)
    {
        this.screen = screen;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(2, 1));

        nameLabel = new JLabel(screen.getName());

        nameLabel.setToolTipText(screen.getOptions().toString());
        add(nameLabel);

        // manipulate panel based on type of screen
        screen.accept(this);
    }

    public void getDialog()
    {
        screen.accept(new ScreenDialog());
    }

    public void update()
    {
        nameLabel.setText(screen.getName());

        if (!screen.getPlayable())
            textLabel.setText(((NonPlayableScreen) screen).getText());
    }

    // modifying preview based on type of screen

    // add nothing other than name
    public void visit(PlayableScreen screen)
    {
        return;
    }

    // add label describing the assignment made
    public void visit(AssignScreen screen)
    {
        textLabel = new JLabel(screen.getText());
        add(textLabel);
    }

    // add label describing the condition in question
    public void visit(CondScreen screen)
    {
        textLabel = new JLabel(screen.getText());
        add(textLabel);
    }

    class ScreenDialog implements ScreenVisitor
    {
        public void visit(PlayableScreen screen) {}

        public void visit(AssignScreen screen)
        {
            JPanel panel = new JPanel();
            JTextField name, newValue;
            JComboBox variable;

            name = new JTextField(screen.getName());
            newValue = new JTextField(screen.getNewValue().toString());
            variable = new JComboBox<>(screen.getParent().getVariables().toArray());

            panel.setLayout(new GridLayout(3, 2));

            panel.add(new JLabel("Name: "));
            panel.add(name);
            panel.add(new JLabel("Variable: "));
            panel.add(variable);
            panel.add(new JLabel("Value: "));
            panel.add(newValue);

            if (JOptionPane.showConfirmDialog(null, panel, "Create assignment screen", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                screen.setName(name.getText());
                screen.setVariable(variable.getSelectedItem().toString());
                screen.setNewValue(newValue.getText());
            }
        }

        public void visit(CondScreen screen)
        {
            JPanel panel = new JPanel();
            JTextField name, pred;

            name = new JTextField(screen.getName());
            pred = new JTextField(screen.getPred().toString());

            panel.setLayout(new GridLayout(3, 2));

            panel.add(new JLabel("Name: "));
            panel.add(name);
            panel.add(new JLabel("Condition: "));
            panel.add(pred);

            if (JOptionPane.showConfirmDialog(null, panel, "Create conditional screen", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                screen.setName(name.getText());
                screen.setPred(pred.getText());
            }
        }
    }
}
