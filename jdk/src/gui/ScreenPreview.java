package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Displays preview image for screens, to be used inside GameEditController.
 * Created by admin on 4/26/16.
 */
public abstract class ScreenPreview extends JPanel
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



        if (screen.getPlayable())
        {
            JLabel nameLabel = new JLabel(name);
            add(nameLabel);
        }
        else if (screen.getClass().equals("gamemodel.CondScreen"))
        {
            CondScreen condScreen = (CondScreen) screen;
            String name = condScreen.getName();
            String condString = condScreen.getPred().toString();

            JLabel nameLabel = new JLabel(name);
            JLabel condLabel = new JLabel(condString);

            add(nameLabel);
            add(condLabel);
        }
        else if (screen.getClass().equals("gamemodel.AssignScreen"))
        {
            AssignScreen assignScreen = (AssignScreen) screen;
            String name = assignScreen.getName();
            String assignment = assignScreen.getName();

            JLabel nameLabel = new JLabel(name);
            JLabel assignLabel = new JLabel(assignment);

            add(nameLabel);
            add(assignLabel);
        }
    }


    // use clicks and double clicks to open a dialog to edit properties or the corresponding ScreenEditController
    public class MouseClicked extends MouseAdapter
    {
        public void MouseClicked(MouseEvent e)
        {
            if (e.getClickCount() == 2)
            {
                // ToDo
            }
            else
            {

            }
        }
    }
}
