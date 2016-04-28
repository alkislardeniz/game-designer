package filemodule.tests;

import filemodule.*;
import gamemodel.Game;

import java.io.IOException;

// tester for Game class serialization
// written by Mehmet Can Altuntaş

public class GameSerializerTest
{
    public static void main(String[] args)
    {
        Game game = new Game();
        Out out = new Out("", game);

        try
        {
            out.serializeFile();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
