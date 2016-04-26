package gui;

import gamemodel.GamePlayer;

/**
 * Window that contains and runs a player.
 * Created by admin on 4/21/16.
 */
public interface PlayerWindow
{
    public GamePlayer getPlayer();

    public void gameOver();
}
