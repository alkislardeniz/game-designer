package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Displays preview image for screens, to be used inside GameEditController.
 * Mehmet was here on 26 April 2016
 * Created by admin on 4/26/16.
 */
public class ScreenPreview extends JPanel implements ScreenVisitor
{
    Screen screen;

    //properties
    private String name;

    // for assignment screens, show the screen's name and assignment
    // for conditional screens, show its name and predicate
    // for playable screens, show its name
    public ScreenPreview(Screen screen)
    {
        this.screen = screen;
        name = screen.getName();

        setPreferredSize(new Dimension(100, 75));
        setLayout(new GridLayout(2, 1));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setToolTipText(screen.getOptions().toString());
        add(nameLabel);

        // manipulate panel based on type of screen
        screen.accept(this);
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
        add(new JLabel(screen.getText()));
    }

    // add label describing the condition in question
    public void visit(CondScreen screen)
    {
        add(new JLabel(screen.getText()));
    }


}
