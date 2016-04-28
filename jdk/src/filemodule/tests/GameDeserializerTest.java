package filemodule.tests;

import filemodule.*;
import gamemodel.Game;

import java.io.IOException;

// tester for Game class serialization
// written by Mehmet Can Altuntaş

public class GameDeserializerTest
{
    public static void main(String[] args)
    {
        In in = new In("C:\\Users\\user\\Desktop\\serialize");

        try
        {
            Game game = (Game) in.deserializeFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
