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
<<<<<<< Updated upstream
        Out out = new Out("C:\\Users\\user\\Desktop\\serialize", game);
=======
        Out out = new Out("", game);
>>>>>>> Stashed changes
        
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
