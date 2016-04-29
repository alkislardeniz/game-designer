package filemodule.tests;

import filemodule.Out;
import gamemodel.Game;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mehmet on 29/04/16.
 */
public class GameTest
{
    public static void main(String[] args)
    {
        Game game = new Game();
        Out out = new Out(new File("save.dadamsave"),game);

        try {
            out.serializeFile();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
