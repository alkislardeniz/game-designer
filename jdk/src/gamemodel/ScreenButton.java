package gamemodel;

/**
 * Created by admin on 4/3/16.
 */
public class ScreenButton extends ScreenComponent
{
    String option;
    String text;
    boolean visible; // if invisible, an object will call it from its keyboard listener

    // possibly rearrange arguments
    public ScreenButton(Screen par, String nam)
    {
        // TODO
    }

    // getters, setters

    // call parent screen if clicked
    // called by game's action listeners
    public void clicked(GamePlayer player)
    {
        // TODO
    }

    // return true if not visible, call super.isCompatible() otherwise
    // if not visible and other is an object, call clicked (from the player, perhaps)
    @Override
    public boolean isCompatible(ScreenComponent other, int x, int y)
    {
        // TODO
        return false;
    }
}