package gui;

import javax.swing.*;
import gamemodel.*;
import java.awt.event.*;

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

        // have screenView at the center of the panel,
        // and a pane of components to add to the left
        setFocusable(true);
    }

    public GamePlayer getPlayer()
    {
        return null;
    }

    // TODO include methods to communicate with options panel and create and move objects

    // if a new component is created, update screenView.comps
}
