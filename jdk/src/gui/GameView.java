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
    JPanel panel;

    public GameView(GameEditController controller)
    {
        super(new BorderLayout());

        JScrollPane scrollPane;

        this.controller = controller;
        game = controller.game;

        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                GameView.this.update();

                for (ScreenPreview view : screens)
                    view.paintOptions(GameView.this, g);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        screens = new ArrayList<>();

        for (Screen screen : game.getScreens())
            addScreen(screen);

        // add scroll pane
        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 575));

        setPreferredSize(new Dimension(800, 575));

        add(scrollPane);

        repaint();
    }

    public void addScreen(Screen screen)
    {
        ScreenPreview preview = new ScreenPreview(screen);
        screens.add(preview);

        PreviewListener listener = new PreviewListener(preview);
        preview.addMouseListener(listener);
        preview.addMouseMotionListener(listener);

        panel.add(Box.createRigidArea(new Dimension(100, 50)));
        panel.add(preview);

        validate();
        repaint();
    }

    public void removePreview(ScreenPreview view)
    {
        // cannot remove start screen
        if (view.screen.equals(game.getStartScreen()))
            return;

        screens.remove(view);
        panel.remove(view);
        game.removeScreen(view.screen.getName());
        controller.pane.pane.removeTabAt(controller.pane.pane.indexOfTab(view.screen.getName()));

        validate();
        repaint();
    }

    public boolean contains(Screen screen)
    {
        for (ScreenPreview view : screens)
            if (view.screen.equals(screen))
                return true;

        return false;
    }

    public Point getLocation(Screen screen)
    {
        for (ScreenPreview view : screens)
            if (view.screen.equals(screen))
                return view.getLocation();

        return null;
    }

    // suboptimal algorithm
    public int indexOf(Screen screen)
    {
        for (ScreenPreview view : screens)
            if (view.screen.equals(screen))
                return screens.indexOf(view);

        return -1;
    }

    public boolean isAdjacent(Screen scr1, Screen scr2)
    {
        int index1 = indexOf(scr1);
        int index2 = indexOf(scr2);
        int diff = index1 - index2;

        return index1 != -1
            && index2 != -1
            && (diff == -1 || diff == 1);
    }

    public void update()
    {
        for (ScreenPreview view : screens)
            view.update();

        repaint();
    }

    public class PreviewListener extends MouseAdapter
    {
        ScreenPreview view;

        public PreviewListener(ScreenPreview view)
        {
            this.view = view;
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            view.showOptions = true;

            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            view.showOptions = false;

            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (controller.deleting)
            {
                removePreview(view);
                validate();
                controller.repaint();
            }
            else
            {
                if (view.screen.getPlayable())
                {
                    controller.pane.addScreen((PlayableScreen) view.screen);
                    validate();
                }
                else
                {
                    view.getDialog();
                }
            }

            for (ScreenPreview view : screens)
                view.update();

            repaint();
        }
    }
}
