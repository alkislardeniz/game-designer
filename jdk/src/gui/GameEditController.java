package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 4/26/16.
 */
public class GameEditController extends JPanel
{
    Game game;
    GameView gameView;
    GameEditTabbedPane pane;
    VariableList vars;

    public GameEditController(Game game)
    {
        this.game = game;

        gameView = new GameView(this);

        pane = new GameEditTabbedPane(this);
        vars = new VariableList(game);

        // add components to panel
        setLayout(new BorderLayout());
        add(pane);

        JPanel leftPanel = new JPanel();

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(new ScreenAddPanel(this), BorderLayout.NORTH);
        leftPanel.add(vars, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
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
        gameView.add(new ScreenPreview(assign));

        gameView.repaint();
    }

    public void createCondScreen(String name, String pred, String trueScreen, String falseScreen)
    {
        CondScreen cond = new CondScreen(game, name);

        cond.setPred(pred);
        cond.addOption("true", game.getScreen(trueScreen));
        cond.addOption("false", game.getScreen(falseScreen));

        game.addScreen(cond);
        gameView.add(new ScreenPreview(cond));

        gameView.repaint();

    }
    public void createPlayableScreen(String name)
    {
        PlayableScreen screen = new PlayableScreen(game, name);

        game.addScreen(screen);
        gameView.add(new ScreenPreview(screen));

        gameView.repaint();
    }
}
