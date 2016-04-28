package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents each screen of the game in a graph.
 * Created by admin on 4/26/16.
 */
public class GameView extends JPanel
{
    Game game;
    ArrayList<ScreenPreview> screens;

    public GameView(GameEditController controller)
    {
        game = controller.game;

        setLayout(new GridLayout());

        screens = new ArrayList<>();
        for (Screen screen : game.getScreens())
            add(new ScreenPreview(screen));
    }

    public void add(ScreenPreview screen)
    {
        screens.add(screen);

        // TODO detect position of screen in grid first
        super.add(screen);
    }
}
