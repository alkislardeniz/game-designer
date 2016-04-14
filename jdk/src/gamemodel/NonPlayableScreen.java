package gamemodel;

/**
 * NonPlayableScreen
 * Performs task and segues into next screen.
 * @author  Ata Deniz Aydin
 * @version 05/04/16
 */
public abstract class NonPlayableScreen extends Screen
{
    public NonPlayableScreen() { playable = false; }

    // set other properties to default values, to be changed by edit description screen
    public NonPlayableScreen(Game parent, String name)
    {
        super(parent, name);
        playable = false;
    }

    @Override
    public void fromPlayer(GamePlayer player)
    {
        toPlayer(player, getOption(player));
    }

    public abstract Option getOption(GamePlayer player);
}
