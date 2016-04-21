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
        AssignScreen scr = new AssignScreen(set, "");

        set.addVariable("x");
        set.setVariable("x", "2 + 3");
        scr.setVariable("x");

        scr.setNewValue("2 + x"); // assign initial values in the GamePlayer class

        set.addScreen(scr);
        scr.addOption("option 1", null);
        set.setStartScreen(scr);

        env = new GamePlayer(set);

        // scr.fromPlayer(env);
        // scr.fromPlayer(env);
        env.addObserver(new GameObserver(env));
        env.call();

        System.out.println(env.getVariable(new Var("x")));

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
            if (player.getCurrentScreen() != null)
                System.out.println(player.getCurrentScreen().getName() + "entered");
        }
    }
}
