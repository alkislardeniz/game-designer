package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by admin on 4/18/16.
 */
public class ScreenViewTest2
{
    public static void main( String[] args)
    {
        Game game = new Game();
        PlayableScreen screen = new PlayableScreen(game, "a");
        PlayableScreen screen2 = new PlayableScreen(game,"d");
        AssignScreen assign = new AssignScreen(game, "b");

        game.addVariable("x");
        game.setVariable("x", "2");

        game.setStartScreen(screen);

        GamePlayer player = new GamePlayer(game);

        ScreenLabel label = new ScreenLabel(screen, "label", "$x + 5 = $(x + 5)");
        ScreenButton button = new ScreenButton(screen, "hello");
        ScreenButton button2 = new ScreenButton(screen2,"hi");

        ScreenObject object = new ScreenObject(screen, "megaman", ObjectIcon.MEGAMAN);
        ScreenObject object2 = new ScreenObject(screen2, "tree", ObjectIcon.TREE);

        button.setPosition(new Point(1, 1));
        button2.setPosition(new Point ( 5,5 ));

        object.setPosition(new Point(5, 5));
        object2.setPosition(new Point(10,10));
        label.setPosition(new Point(7, 7));

        screen.addComponent(button);
        screen.addComponent(object);
        screen.addComponent(label);
        screen.addComponent(object2);

        screen2.addComponent(button2);
        screen2.addComponent(object2);
        screen2.addComponent(object);

        screen.setMovable(object);
        screen2.setMovable(object);

        button.setVisible(false);
        button.setOption("option 2");
        button2.setOption("option 3");

        screen.addOption("option 1", assign);
        screen.addOption("option 2", screen2);
        screen2.addOption("option 3",screen);

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
            // player.addObserver(this);
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
