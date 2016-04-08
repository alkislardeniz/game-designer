package gamemodel;

/**
 * Option
 * Stores information about an option of the screen.
 * @author  Ata Deniz Aydin
 * @version 08/04/16
 */
public class Option
{
    String name;
    Screen screen;

    public Option(String name, Screen screen)
    {
        this.name   = name;
        this.screen = screen;
    }

    public boolean equals(Object other)
    {
        return other != null
            && other instanceof Option
            && ((Option) other).name.equals(name);
    }

    public boolean hasName(String name)
    {
        return this.name.equals(name);
    }

    public String getName()
    {
        return name;
    }

    public Screen getScreen()
    {
        return screen;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public void setScreen(Screen newScreen)
    {
        screen = newScreen;
    }
}
