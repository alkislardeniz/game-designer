package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 4/18/16.
 */
public class ScreenViewTest
{
    public static void main( String[] args)
    {
        Game game = new Game();
        GamePlayer player = new GamePlayer(game);
        PlayableScreen screen = new PlayableScreen(game, "a");

        ScreenLabel label = new ScreenLabel(screen, "", "2 + 5");
        ScreenButton button = new ScreenButton(screen, "hello");
        ScreenObject object = new ScreenObject(screen, "megaman", ObjectIcon.MEGAMAN);

        button.setPosition(new Point(1, 1));
        object.setPosition(new Point(5, 5));
        label.setPosition(new Point(2, 2));

        screen.addComponent(button);
        screen.addComponent(object);
        screen.addComponent(label);

        screen.setMovable(object);

        button.setVisible(false);
        button.setOption("option 1");
        screen.addOption("option 1", screen);

        player.addObserver(new GameObserver(player));

        ImageIcon logo = new ImageIcon ("pics/logo.png");
        JFrame f = new JFrame ("Dadam - Game Designer");
        f.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setSize(520, 620);

        ScreenPlayController controller = new ScreenPlayController(player, screen);

        f.add(controller);
        f.add(new JLabel(logo), BorderLayout.NORTH);

        f.setVisible( true);
    }

    private static class GameObserver implements Observer
    {
        private GamePlayer player;

        public GameObserver(GamePlayer player)
        {
            this.player = player;
            // player.addObserver(this);
        }

        public void update()
        {
            // System.out.println(player.getVariable(new Var("x")));

            if (player.getCurrentScreen() != null)
                System.out.println(player.getCurrentScreen().getName() + " entered");
        }
    }

}
