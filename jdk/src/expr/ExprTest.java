package expr;

import gamemodel.*;

/**
 * Created by admin on 4/9/16.
 */
public class ExprTest
{
    public static void main(String[] args)
    {
        String str = "-2 + 3 * (x - 7) + 3";
        Game set = new Game();
        GamePlayer env;
        AssignScreen scr = new AssignScreen(set, "a");
        PlayableScreen scr1 = new PlayableScreen(set, "b");
        CondScreen scr2 = new CondScreen(set, "c");
        ScreenButton button = new ScreenButton(scr1, "button");

        set.addVariable("x");
        set.setVariable("x", "2 + 3");
        scr.setVariable("x");

        scr.setNewValue("2 + x"); // assign initial values in the GamePlayer class

        set.addScreen(scr);
        set.addScreen(scr1);

        scr.addOption("option 1", scr2);
        scr1.addOption("option 1", scr);

        button.setOption("option 1");
        scr1.addComponent(button);

        scr2.addOption("option 1", scr);
        scr2.addOption("option 2", null);

        scr2.setPred("x < 12");

        set.addScreen(scr2);

        set.setStartScreen(scr);

        env = new GamePlayer(set);

        System.out.println(env.getVariable(new Var("x")));

        // scr.fromPlayer(env);
        // scr.fromPlayer(env);
        env.addObserver(new GameObserver(env));
        env.call();

        // button.clicked(env);

        System.out.println(env.getVariable(new Var("x")));

        // System.out.println(env.getVariable(new Var("x")));

        System.out.println(str + " = " + Expr.parse(str).eval(env));
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
