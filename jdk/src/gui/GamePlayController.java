package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Changes ScreenPlayController based on current screen of game
 * Created by admin on 4/15/16.
 */
public class GamePlayController extends JPanel implements Observer
{
    GamePlayer player;
    PlayableScreen currentScreen; // if player's current screen changes, change ScreenPlayController based on it

    public GamePlayController(GamePlayer player)
    {
        this.player = player;
        currentScreen = (PlayableScreen) player.getCurrentScreen();

        // TODO create ScreenPlayController, include it in panel
    }

    @Override
    public void update()
    {
        currentScreen = (PlayableScreen) player.getCurrentScreen(); // make sure getCurrentScreen() is playable
        // TODO update ScreenPlayController if currentScreen != null
    }
}
