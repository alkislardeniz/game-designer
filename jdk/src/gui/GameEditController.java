package gui;

import gamemodel.*;
import javax.swing.*;

/**
 * Created by admin on 4/26/16.
 */
public class GameEditController extends JPanel
{
    Game game;
    // tabbed pane
    // variable scroll pane and 3 buttons for creating new screens
    GameView gameView;

    public GameEditController(Game game)
    {
        this.game = game;
        this.gameView = new GameView(this);
    }

    // methods

    /* TODO
     * - include tabbed pane to add game view and screen edit controllers to
     * - create graphical interface for game view
     * - create panel containing screen add/delete buttons and a scroll pane for variables
     */

    // create new assignment screen
    public void createAssignScreen(String name, String varName, String newValue, String newScreen)
    {
        AssignScreen assign = new AssignScreen(game, name);

        assign.setVariable(varName);
        assign.setNewValue(newValue);
        assign.addOption("default", game.getScreen(newScreen));

        game.addScreen(assign);
        gameView.add(new ScreenPreview(assign));
    }

    public void createCondScreen(String name, String pred, String trueScreen, String falseScreen)
    {
        CondScreen cond = new CondScreen(game, name);

        cond.setPred(pred);
        cond.addOption("true", game.getScreen(trueScreen));
        cond.addOption("false", game.getScreen(falseScreen));

        game.addScreen(cond);
        gameView.add(new ScreenPreview(cond));

    }
    public void createPlayableScreen(String name)
    {
        PlayableScreen screen = new PlayableScreen(game, name);

        game.addScreen(screen);
        gameView.add(new ScreenPreview(screen));
    }
}
