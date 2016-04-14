package gamemodel;

/**
 * Created by admin on 4/3/16.
 */
public class ScreenObject extends ScreenComponent
{
    boolean movable; // there are two types of objects, movable and immovable
    String img;
    // ...

    public ScreenObject(Screen par, String nam, String img)
    {
        // TODO
    }

    // getters, setters

    public String getIcon() { return img; }

    // perhaps create new ObjectPlayer class to manage object movement in playing game
}