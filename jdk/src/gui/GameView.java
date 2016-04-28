package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        screens = new ArrayList<>();
        for (Screen screen : game.getScreens())
        {
            screens.add(new ScreenPreview(screen));
        }

        setLayout(new GridLayout(screens.size(), 1));
        for (ScreenPreview screenPreview : screens)
        {
            add(screenPreview);
        }
    }

    public void add(ScreenPreview screen)
    {
        screens.add(screen);

        // TODO detect position of screen in grid first
        super.add(screen);
    }


}
