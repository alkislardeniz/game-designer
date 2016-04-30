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
public class GamePlayController extends JPanel implements Observer, GameController
{
    PlayerWindow parent;
    GamePlayer player;
    PlayableScreen currentScreen; // if player's current screen changes, change ScreenPlayController based on it
    ScreenPlayController controller;

    public GamePlayController(GamePlayer player)
    {
        this.player = player;

        currentScreen = (PlayableScreen) player.getCurrentScreen();
        controller = new ScreenPlayController(player, currentScreen);
        player.addObserver(this);
        add(controller);
    }

    public GamePlayController(PlayerWindow parent)
    {
        this.parent = parent;
        this.player = parent.getPlayer();

        currentScreen = (PlayableScreen) player.getCurrentScreen();
        controller = new ScreenPlayController(player, currentScreen);
        player.addObserver(this);
        add(controller);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        // panel.setFocusable(false);
        currentScreen = (PlayableScreen) player.getCurrentScreen(); // currentScreen is playable every time notifyObservers() is called

        if (currentScreen != null)
        {
            controller.setScreen(currentScreen);

            revalidate();
            repaint();
        }
        else if (parent != null)
        {
            // handle game over
            parent.gameOver();
        }
    }
}
