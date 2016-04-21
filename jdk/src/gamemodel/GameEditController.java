package gamemodel;

/**
 * Created by user on 21.04.2016.
 */
public class GameEditController

{
    Game newGame;

    public GameEditController(Game newGame )
    {
        this.newGame = newGame;
    }

    // methods
    public void createAssignScreen(String name)
    {
        newGame.addScreen(  new AssignScreen(newGame,name) );
    }
    public void createCondScreen(String name)
    {
        newGame.addScreen( new CondScreen(newGame, name));

    }
    public void createPlayableScreen(String name)
    {
        //TODO tell the gui

    }


}
