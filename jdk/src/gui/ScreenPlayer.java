package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;

/**
 * Contains ScreenView for a screen being played.
 * Created by admin on 4/12/16.
 */
public class ScreenPlayer extends JPanel
{
    PlayableScreen screen;
    GamePlayer player;
    ScreenView view;

    public ScreenPlayer(GamePlayer player, PlayableScreen screen)
    {
        this.player = player;
        this.screen = screen;

        view = new ScreenView(screen);
        add(view);

        // TODO find a way to move objects inside the screen without changing their starting position in the game
        // Perhaps copy each component in the screen?
    }

    // TODO add keyboard listener that moves the movable object, and action listeners for buttons
}
