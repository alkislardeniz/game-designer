package gui;

import expr.Binding;
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

        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createLineBorder(Color.black));

        nameLabel = new JLabel(screen.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setToolTipText(getOptionList());
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
        setToolTipText(getOptionList());

        nameLabel.setText(screen.getName());

        if (!screen.getPlayable())
            textLabel.setText(((NonPlayableScreen) screen).getText());

        repaint();
    }

    private String getOptionList()
    {
        String res = "";

        if (screen.getOptions().isEmpty())
            return res;

        for (Option o : screen.getOptions())
            res = res + "\n" + o.getName() + " : " + o.getScreen();

        return res.substring(1);
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
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(textLabel);
    }

    // add label describing the condition in question
    public void visit(CondScreen screen)
    {
        textLabel = new JLabel(screen.getText());
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(textLabel);
    }

    class ScreenDialog implements ScreenVisitor
    {
        public void visit(PlayableScreen screen) {}

        public void visit(AssignScreen screen)
        {
            JPanel panel = new JPanel();
            JTextField name, newValue;
            JComboBox variable, next;

            name = new JTextField(screen.getName());
            newValue = new JTextField(screen.getNewValue().toString());
            variable = new JComboBox<>(screen.getParent().getVariables().toArray(new Binding[0]));
            next = new JComboBox<>(screen.getParent().getScreens().toArray(new Screen[0]));

            panel.setLayout(new GridLayout(4, 2));

            panel.add(new JLabel("Name: "));
            panel.add(name);
            panel.add(new JLabel("Variable: "));
            panel.add(variable);
            panel.add(new JLabel("Value: "));
            panel.add(newValue);
            panel.add(new JLabel("Next screen: "));
            panel.add(next);

            if (JOptionPane.showConfirmDialog(null, panel, "Modify assignment screen", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                screen.setName(name.getText());
                screen.setVariable(variable.getSelectedItem().toString());
                screen.setNewValue(newValue.getText());
                screen.setOption("default", (Screen) next.getSelectedItem());
            }
        }

        public void visit(CondScreen screen)
        {
            JPanel panel = new JPanel();
            JTextField name, pred;
            JComboBox trueScreen, falseScreen;

            name = new JTextField(screen.getName());
            pred = new JTextField(screen.getPred().toString());

            trueScreen  = new JComboBox<>(screen.getParent().getScreens().toArray(new Screen[0]));
            falseScreen = new JComboBox<>(screen.getParent().getScreens().toArray(new Screen[0]));

            panel.setLayout(new GridLayout(4, 2));

            panel.add(new JLabel("Name: "));
            panel.add(name);
            panel.add(new JLabel("Condition: "));
            panel.add(pred);
            panel.add(new JLabel("True screen: "));
            panel.add(trueScreen);
            panel.add(new JLabel("False screen: "));
            panel.add(falseScreen);

            if (JOptionPane.showConfirmDialog(null, panel, "Modify conditional screen", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                screen.setName(name.getText());
                screen.setPred(pred.getText());
                screen.setOption("true", (Screen) trueScreen.getSelectedItem());
                screen.setOption("false", (Screen) falseScreen.getSelectedItem());
            }
        }
    }
}
