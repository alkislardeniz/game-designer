package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    PlayableScreen screen = new PlayableScreen(game, "a");
    AssignScreen assign = new AssignScreen(game, "b");
    JPanel panel;

    public GameView(GameEditController controller)
    {
        super(new BorderLayout());

        JScrollPane scrollPane;

        this.controller = controller;
        game = controller.game;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        screens = new ArrayList<>();

        for (Screen screen : game.getScreens())
            addScreen(screen);

        // add scroll pane
        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 500));

        setPreferredSize(new Dimension(800, 500));

        add(scrollPane);
    }

    public void addScreen(Screen screen)
    {
        ScreenPreview preview = new ScreenPreview(screen);
        screens.add(preview); // estne hoc necesse?
        preview.addMouseListener(new PreviewListener(preview));
        panel.add(preview);

        repaint();
    }

    public void removePreview(ScreenPreview view)
    {
        screens.remove(view);
        panel.remove(view);
        game.removeScreen(view.screen.getName());
    }


    public class PreviewListener extends MouseAdapter implements ActionListener
    {
        ScreenPreview view;
        MouseEvent lastEvent;
        Timer t;

        public PreviewListener(ScreenPreview view)
        {
            this.view = view;

            t = new Timer(500, this);
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getClickCount() > 2)
                return;

            lastEvent = e;

            if (view.screen.getPlayable() && t.isRunning())
            {
                t.stop();

                controller.pane.addScreen((PlayableScreen) view.screen);
                validate();
            }
            else
            {
                t.restart();
            }

            for (ScreenPreview view : screens)
                view.update();

            repaint();
        }

        public void actionPerformed(ActionEvent e)
        {
            t.stop();
            if (controller.deleting)
            {
                removePreview(view);
                validate();
            }
            else
            {
                view.getDialog();
            }
        }
    }
}
