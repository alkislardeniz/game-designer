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

    }

    // getters, setters

    // call parent screen if clicked
    // called by game's action listeners
    public void clicked()
    {

    }

    // return true if not visible, call super.isCompatible() otherwise
    @Override
    public boolean isCompatible(ScreenComponent other)
    {
        return false;
    }
}