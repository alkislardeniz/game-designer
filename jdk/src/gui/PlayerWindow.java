package gui;

import gamemodel.GamePlayer;

/**
 * Window that contains and runs a player.
 * @author  Ata Deniz Aydin
 * @version 21/04/16
 */
public interface PlayerWindow
{
    public GamePlayer getPlayer();

    public void gameOver();
}
