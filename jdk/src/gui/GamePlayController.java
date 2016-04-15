package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Changes ScreenPlayController based on current screen of game
 * Created by admin on 4/15/16.
 */
public class GamePlayController extends JPanel
{
    GamePlayer player;
    Screen currentScreen; // if player's current screen changes, change ScreenPlayController based on it

    public GamePlayController(GamePlayer player)
    {
        this.player = player;
        currentScreen = player.getCurrentScreen();

        // TODO create ScreenPlayController, include it in panel
    }

    class ScreenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (!player.getCurrentScreen().equals(currentScreen))
            {
                currentScreen = player.getCurrentScreen(); // make sure getCurrentScreen() is playable
                // TODO change ScreenPlayController
            }
        }
    }
}
