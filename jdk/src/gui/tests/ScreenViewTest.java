package gui.tests;

import gamemodel.*;
import gui.GamePlayController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Mehmet was here uga buga luga duga muga suga kuga aga daga dega waga maga taga daga baga laga raga lagu ragu
 */
public class ScreenViewTest
{
    public static void main( String[] args)
    {
        Game game = new Game();
        PlayableScreen screen = new PlayableScreen(game, "a");
        AssignScreen assign = new AssignScreen(game, "b");

        game.addVariable("x");
        game.setVariable("x", "2");

        game.setStartScreen(screen);

        GamePlayer player = new GamePlayer(game);

        ScreenLabel label = new ScreenLabel(screen, "label", "$x + 5 = $(x + 5)");
        ScreenButton button = new ScreenButton(screen, "hello");
        ScreenObject object = new ScreenObject(screen, "megaman", ObjectIcon.MEGAMAN);
        ScreenObject bg = new ScreenObject(screen, "bg", ObjectIcon.BG);


        button.setPosition(new Point(1, 1));
        object.setPosition(new Point(0, 0));
        label.setPosition(new Point(7, 7));

        screen.addComponent(bg);
        screen.addComponent(button);
        screen.addComponent(object);
        screen.addComponent(label);

        screen.setMovable(object);

        button.setVisible(false);
        button.setOption("option 1");
        screen.addOption("option 1", assign);

        assign.setVariable("x");
        assign.setNewValue("x + 1");
        assign.addOption("option", screen);

        // player.addObserver(new GameObserver(player, object));
        // button.clicked(player);

        ImageIcon logo = new ImageIcon ("pics/logo.png");
        JFrame f = new JFrame ("Dadam - Game Designer");
        f.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setSize(520, 620);

        // ScreenPlayController controller = new ScreenPlayController(player, screen);
        GamePlayController controller = new GamePlayController(player);

        f.add(controller);
        f.add(new JLabel(logo), BorderLayout.NORTH);

        f.setVisible(true);
    }

    private static class GameObserver implements Observer
    {
        private GamePlayer player;
        private ScreenObject object;

        public GameObserver(GamePlayer player, ScreenObject object)
        {
            this.player = player;
            this.object = object;
        }

        public void update(Observable o, Object arg)
        {
            // fix it so the position of the object is unchanged by playing, by copying movableComponent
            System.out.println(object.getPosition());

            if (player.getCurrentScreen() != null)
                System.out.println(player.getCurrentScreen().getName() + " entered");
        }
    }

}
