package gui;

import javax.swing.*;
import gamemodel.*;
import java.awt.*;

/**
 * Panel containing ScreenView that allows to edit an individual screen.
 * Created by Ata Deniz Aydin on 4/12/16.
 */
public class ScreenEditController extends JPanel implements ScreenController
{
    Game game;
    PlayableScreen screen;
    ScreenView screenView;
    EditScrollPaneRight scrollPaneRight;
    EditScrollPaneLeft scrollPaneLeft;
    EditScreenOptions screenOptions;

    public ScreenEditController(PlayableScreen screen)
    {
        this.screen = screen;
        game = screen.getParent();

        screenView = new ScreenView(this, screen);
        add(screenView);

        scrollPaneRight = new EditScrollPaneRight(this);
        scrollPaneLeft = new EditScrollPaneLeft(this);
        screenOptions = new EditScreenOptions(this);

        setLayout(new BorderLayout());

        add(screenView, BorderLayout.CENTER);
        add(scrollPaneRight, BorderLayout.EAST);
        add(scrollPaneLeft, BorderLayout.WEST);
        add(screenOptions, BorderLayout.SOUTH);

        //setPreferredSize (new Dimension (504, 264));

        // have screenView at the center of the panel,
        // and a pane of components to add to the left
        setFocusable(true);
    }

    public GamePlayer getPlayer()
    {
        return null;
    }

    // update whether to show grid
    public void updateShowGrid(boolean shouldShowGrid)
    {
        screenView.setShowGrid(shouldShowGrid);
    }
    
    // update selected component
    // perhaps carry reference instead of string
    public void updateSelectedComponent(String selectedComponent)
    {
        ScreenComponent newComp;

        // check if can add component
        if (!screenOptions.shouldDelete())
        {
            for (ObjectIcon icon : ObjectIcon.values())
            {
                if (selectedComponent.equals(icon.toString()))
                {
                    newComp = new ScreenObject(screen, icon.toString(), icon);
                    newComp.setPosition(screenView.getObjectAddDeletePos());
                    newComp.accept(screenView);
                }
            }

            // TODO also check for label, button and text box
        }

        // update screen
        repaint();
    }
}
