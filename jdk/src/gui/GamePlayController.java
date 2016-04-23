package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Changes ScreenPlayController based on current screen of game
 * Created by admin on 4/15/16.
 */
public class GamePlayController extends JPanel implements Observer
{
    PlayerWindow parent;
    GamePlayer player;
    PlayableScreen currentScreen; // if player's current screen changes, change ScreenPlayController based on it
    ScreenPlayController controller;

    public GamePlayController(PlayerWindow parent)
    {
        this.parent = parent;
        this.player = parent.getPlayer();

        currentScreen = (PlayableScreen) player.getCurrentScreen();
        controller = new ScreenPlayController(player, currentScreen);
        add(controller);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        currentScreen = (PlayableScreen) player.getCurrentScreen(); // currentScreen is playable every time notifyObservers() is called

        if (currentScreen != null)
        {
            remove(controller);
            controller = new ScreenPlayController(player, currentScreen);
            add(controller);

            validate();
            repaint();
        }
        else
        {
            // handle game over
            parent.gameOver();
        }
    }
}
