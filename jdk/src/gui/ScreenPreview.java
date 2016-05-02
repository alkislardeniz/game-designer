package gui;

import expr.Binding;
import gamemodel.*;
import javax.swing.*;
import java.awt.*;
import java.lang.Math;

/**
 * Displays preview image for screens, to be used inside GameEditController.
 * @author  Mehmet Can Altuntas
 * @author  Ata Deniz Aydin
 * @version 01/05/16
 */
public class ScreenPreview extends JPanel implements ScreenVisitor
{
    static final int WIDTH = 100;
    static final int HEIGHT = 75;

    Screen screen;
    boolean showOptions;
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
        String res;

        if (screen.getOptions().isEmpty())
            return "No options";

        res = "Options:";
        for (Option o : screen.getOptions())
            res = res + "\n" + o.getName() + " : " + o.getScreen();

        return res;
    }

    // paint each option as an arrow if highlighted
    public void paintOptions(GameView parent, Graphics g)
    {
        Point next;
        int offset = 100;
        int direction; // 1 if going down, -1 if up
        int x, y, nextX, nextY;

        // center (x,y)
        x = getX() + WIDTH / 2;
        y = getY() + HEIGHT / 2;

        if (showOptions)
        {
            // for each option,
            //   find location of next screen in view
            //   if adjacent, draw straight line
            //   otherwise draw lines in alternating sides and in increasing offset

            for (Option op : screen.getOptions())
            {
                next = parent.getLocation(op.getScreen());

                if (next != null)
                {
                    nextX = (int) next.getX() + WIDTH / 2;
                    nextY = (int) next.getY() + HEIGHT / 2;
                    direction = (int) Math.signum(next.getY() - getY());

                    // directing back to itself
                    if (direction == 0)
                    {
                        // draw full loop
                        g.drawLine(x, y, x - 100, y);
                        g.drawLine(x - 100, y, x - 100, y - 85);
                        g.drawLine(x - 100, y - 85, x, y - 85);
                        g.drawLine(x, y - 85, x, y);

                        // draw name
                        g.drawString(op.getName(), x - 100, y - 10);

                    }
                    else if (parent.isAdjacent(screen, op.getScreen()))
                    {
                        // draw line
                        g.drawLine(x, y, nextX, nextY);

                        // draw name
                        g.drawString(op.getName(), x + 5, y + direction * 50);
                    }
                    else if (parent.contains(op.getScreen()))
                    {
                        // draw loop based on offset
                        g.drawLine(x, y, x + offset, y);
                        g.drawLine(x + offset, y, x + offset, nextY);
                        g.drawLine(x + offset, nextY, x, nextY);

                        int width = g.getFontMetrics().stringWidth(op.getName());

                        // draw name
                        g.drawString(op.getName(), x + offset - width * (int) (Math.signum(offset) + 1) / 2, y - 5);

                        // update offset
                        offset = -(offset + 25);
                    }
                }
            }
        }
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
        public void visit(PlayableScreen screen)
        {
        }

        public void visit(AssignScreen screen)
        {
            JPanel panel = new JPanel();
            JTextField newValue;
            JComboBox variable, next;

            newValue = new JTextField(screen.getNewValue().toString());
            variable = new JComboBox<>(screen.getParent().getVariables().toArray(new Binding[0]));
            next = new JComboBox<>(screen.getParent().getScreens().toArray(new Screen[0]));

            panel.setLayout(new GridLayout(4, 2));

            panel.add(new JLabel("Variable: "));
            panel.add(variable);
            panel.add(new JLabel("Value: "));
            panel.add(newValue);
            panel.add(new JLabel("Next screen: "));
            panel.add(next);

            if (JOptionPane.showConfirmDialog(null, panel, "Modify assignment screen " + screen.getName(), JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                if (variable.getSelectedItem() == null || newValue.getText() == null
                     || next.getSelectedItem() == null || newValue.getText().isEmpty())
                    return;

                screen.setVariable(variable.getSelectedItem().toString());
                screen.setNewValue(newValue.getText());
                screen.setOption("default", (Screen) next.getSelectedItem());
            }
        }

        public void visit(CondScreen screen)
        {
            JPanel panel = new JPanel();
            JTextField pred;
            JComboBox trueScreen, falseScreen;

            pred = new JTextField(screen.getPred().toString());

            trueScreen  = new JComboBox<>(screen.getParent().getScreens().toArray(new Screen[0]));
            falseScreen = new JComboBox<>(screen.getParent().getScreens().toArray(new Screen[0]));

            panel.setLayout(new GridLayout(4, 2));

            panel.add(new JLabel("Condition: "));
            panel.add(pred);
            panel.add(new JLabel("True screen: "));
            panel.add(trueScreen);
            panel.add(new JLabel("False screen: "));
            panel.add(falseScreen);

            if (JOptionPane.showConfirmDialog(null, panel, "Modify conditional screen " + screen.getName(), JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                screen.setPred(pred.getText());
                screen.setOption("true", (Screen) trueScreen.getSelectedItem());
                screen.setOption("false", (Screen) falseScreen.getSelectedItem());
            }
        }
    }
}
