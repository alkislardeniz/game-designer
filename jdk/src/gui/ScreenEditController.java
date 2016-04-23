package gui;

import javax.swing.*;
import gamemodel.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel containing ScreenView that allows to edit an individual screen.
 * Created by admin on 4/12/16.
 */
public class ScreenEditController extends JPanel implements ScreenController
{
    Game game;
    PlayableScreen screen;
    ScreenView screenView;
    EditScrollPane scrollPane;
    EditScreenOptions screenOptions;

    // TODO also include a panel containing options for components to add to the screen

    public ScreenEditController(PlayableScreen screen)
    {
        this.screen = screen;
        game = screen.getParent();

        screenView = new ScreenView(this, screen);
        add(screenView);

        scrollPane = new EditScrollPane();
        screenOptions = new EditScreenOptions();

        setLayout (new BorderLayout());

        add (screenView, BorderLayout.CENTER);
        add (scrollPane, BorderLayout.EAST);
        add (screenOptions, BorderLayout.SOUTH);

        //setPreferredSize (new Dimension (504, 264));

        // have screenView at the center of the panel,
        // and a pane of components to add to the left
        setFocusable(true);
    }

    public GamePlayer getPlayer()
    {
        return null;
    }

    //this method updates gridShow and adds components to the screenView
    public void screenViewUpdate()
    {
        //updating grid show
        if (screenOptions.isGridShow()) {
            screenView.setShowGrid(true);
        }
        else {
            screenView.setShowGrid(false);
        }

        //adding components
        if (screenOptions.getIsDelete() == false) {
            if (scrollPane.getSelectedObject().equals("ROCK")) {
                ScreenObject newObj = new ScreenObject(screen, "ROCK", ObjectIcon.ROCK);
                newObj.setPosition(screenView.getObjectAddDeletePos());
                screenView.visit(newObj);
            }
            else if (scrollPane.getSelectedObject().equals("TREE")) {
                ScreenObject newObj = new ScreenObject(screen, "TREE", ObjectIcon.TREE);
                newObj.setPosition(screenView.getObjectAddDeletePos());
                screenView.visit(newObj);
            }
        }
        //TODO find a way to add screen objects without using if statements
        //TODO find a way to add label, button and text box
    }
}
