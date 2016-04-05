package gamemodel;

/**
 * NonPlayableScreen
 * Performs task and segues into next screen.
 * @author  Ata Deniz Aydin
 * @version 05/04/16
 */
public abstract class NonPlayableScreen extends Screen
{
    @Override
    public void fromPlayer(GamePlayer player)
    {
        toPlayer(player, getOption(player));
    }

    public abstract String getOption(GamePlayer player);
}
