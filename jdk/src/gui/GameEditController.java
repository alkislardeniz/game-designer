package gui;

import gamemodel.*;

/**
 * Created by admin on 4/26/16.
 */
public class GameEditController
{
    Game game;

    public GameEditController(Game game)
    {
        this.game = game;
    }

    // methods

    // create new assignment screen
    public void createAssignScreen(String name, String varName, String newValue, String newScreen)
    {
        AssignScreen assign = new AssignScreen(game, name);

        assign.setVariable(varName);
        assign.setNewValue(newValue);
        assign.addOption("default", game.getScreen(newScreen));

        game.addScreen(assign);
    }

    public void createCondScreen(String name, String pred, String trueScreen, String falseScreen)
    {
        CondScreen cond = new CondScreen(game, name);

        cond.setPred(pred);
        cond.addOption("true", game.getScreen(trueScreen));
        cond.addOption("false", game.getScreen(falseScreen));

        game.addScreen(new CondScreen(game, name));

    }
    public void createPlayableScreen(String name)
    {
        // TODO tell the gui


    }

    // have previews for each screen
}
