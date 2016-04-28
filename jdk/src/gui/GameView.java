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
    GameEditController controller;
    Game game;
    ArrayList<ScreenPreview> screens;

    public GameView(GameEditController controller)
    {
        this.controller = controller;
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
        setPreferredSize(new Dimension(ScreenPreview.WIDTH, ScreenPreview.HEIGHT * screens.size()));
    }

    public void add(ScreenPreview screen)
    {
        // TODO detect position of screen in grid first
        screen.addMouseListener(new PreviewListener(screen));
        super.add(screen);
    }

    public class PreviewListener extends MouseAdapter
    {
        ScreenPreview view;

        public PreviewListener(ScreenPreview view)
        {
            this.view = view;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (view.screen.getPlayable())
            {
                // open new screen edit controller
                controller.pane.addScreen((PlayableScreen) view.screen);
            }
            else
            {
                view.getDialog();
            }
        }
    }
}
